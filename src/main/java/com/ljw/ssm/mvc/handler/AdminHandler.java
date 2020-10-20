package com.ljw.ssm.mvc.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Admin;
import com.ljw.ssm.service.api.AdminService;
import com.ljw.ssm.util.CrowdConstand;
import com.ljw.ssm.util.ResultEntity;


@Controller
public class AdminHandler {
	
	Logger loogger=LoggerFactory.getLogger(AdminHandler.class);
	
	@Autowired
	private AdminService adminService;
	

	
	// 删除   
	@PreAuthorize("hasRole('经理操作者') or hasAuthority('user:delete')")
	@RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
	public String removeAdmin(@PathVariable("adminId")Integer adminId,
			@PathVariable("pageNum")Integer pageNum,
			@PathVariable("keyword")String keyword,
			 HttpSession session,
			 HttpServletResponse response,
			 HttpServletRequest request) throws IOException {
		loogger.info(adminId+"----"+pageNum+"-------"+keyword);
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * Admin admin = (Admin)
		 * session.getAttribute(CrowdConstand.ATTP_NAME_LOGIN_ADMIN);
		 * 
		 * if (admin.getId()==adminId) { PrintWriter out = response.getWriter();
		 * out.println("<script>"); out.println("alert('删除失败,你删到自己了')");
		 * out.println("</script>"); out.close(); }else { adminService.remove(adminId);
		 * }
		 */
		adminService.remove(adminId);
		
		return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
		
	}
	
	// 更新
	@PreAuthorize("hasRole('经理操作者') or hasAuthority('user:edit')")
	@RequestMapping("/admin/update.html")
	public String updateAdmin(Admin admin,
			@RequestParam("pageNum")Integer pageNum,
			@RequestParam("keyword")String keyword) {
		loogger.debug(admin.toString());
		adminService.updateAdmin(admin);
		return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
	}
	
	// 更新(去更新页面
	@PreAuthorize("hasRole('经理操作者') or hasAuthority('user:edit')")
	@RequestMapping("/admin/to/edit/page.html")
	public String editPage(@RequestParam("adminId")Integer adminId,
			@RequestParam("pageNum")Integer pageNum,
			@RequestParam("keyword")String keyword,
			Model model) {
		Admin admin=adminService.getAdminById(adminId);
		model.addAttribute("admin", admin);
		
		return "admin-edit";
	}
	
	// 新增用户ajax
	@PreAuthorize("hasRole('经理操作者') or hasAuthority('user:save')")
	@ResponseBody
	@RequestMapping("/admin/save.json")
	public ResultEntity<String> saveUser(Admin admin){
		loogger.debug(admin.toString());
		adminService.saveAdmin(admin);
		return ResultEntity.successWithoutData();
	}
	
	
	// 用户界面(分页）
	@PreAuthorize("hasRole('经理') or hasRole('经理操作者')")
	@RequestMapping("/admin/get/page.html")
	public String getALLPage(@RequestParam(value = "keyword",defaultValue = "")String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
			Model model) {
		PageInfo<Admin> pageInfo=adminService.getPageInfo(keyword,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin-page";
	}
	
	
	// 退出
	@RequestMapping("/to/do/logout.html")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/to/login/page.html";
	}
	
	// 登陆
	@RequestMapping("/admin/do/login.html")
	public String login(@RequestParam("loginAcct")String loginAcct,@RequestParam("userPswd")String userPswd,HttpSession session) {
		Admin admin=adminService.getByAdminLogin(loginAcct,userPswd);
		session.setAttribute(CrowdConstand.ATTP_NAME_LOGIN_ADMIN, admin);
		return "redirect:/admin/to/main/page.html";
		
	}

}
