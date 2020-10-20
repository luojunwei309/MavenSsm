package com.ljw.ssm.mvc.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssm.entity.Auth;
import com.ljw.ssm.entity.Role;
import com.ljw.ssm.service.api.AdminService;
import com.ljw.ssm.service.api.AuthService;
import com.ljw.ssm.service.api.RoleService;
import com.ljw.ssm.util.ResultEntity;

@Controller
public class AuthHandler {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleService;
	
	// 保存角色和权限的关系
	@ResponseBody
	@RequestMapping("/assign/do/role/assign/auth.json")
	public ResultEntity<String> saveRoleAuth(
			@RequestBody Map<String, List<Integer>> map){
		 authService.saveRoleAuth(map);
		return ResultEntity.successWithoutData();
	}
	
	// 查询角色Id查找全部权限
	@ResponseBody
	@RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
	public ResultEntity<List<Integer>> getRoleIdAuth(@RequestParam("roleId")Integer roleId){
		List<Integer> authList=authService.getRoleIdAuth(roleId);
		
		return ResultEntity.successWithData(authList);
	}
	
	// 查询全部权限
	@ResponseBody
	@RequestMapping("/assign/get/all/auth.json")
	public ResultEntity<List<Auth>> getAllAuth(){
		List<Auth> authList=authService.getAll();
		return ResultEntity.successWithData(authList);
	}
	
	// 保存admin新的角色
	@RequestMapping("/assign/do/role/assign.html")
	public String saveAdminRoleRelationship(@RequestParam("adminId")Integer adminId,
			@RequestParam("pageNum")Integer pageNum,
			@RequestParam(value = "keyword",defaultValue = "")String keyword,
			@RequestParam(value="roleIdList",required = false)List<Integer> roleIdList) {
		
		adminService.saveAdminRole(adminId,roleIdList);
		
		return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
	}

	// 查找用户和角色的关联
	@RequestMapping("/assign/to/assign/role/page.html")
	public String adminRole(@RequestParam("adminId")Integer adminId,Model model) {
		// ?adminId=${admin.id }&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}
		
		// 查找跟这个adminId关联的角色
		List<Role> Allocated=roleService.getAllocated(adminId);
		model.addAttribute("Allocated", Allocated);
		
		// 查找跟这个adminId没有关联的角色
		List<Role> Undestrbute=roleService.getUndestrbute(adminId);
		model.addAttribute("Undestrbute", Undestrbute);
		
		return "assign-role";
	}
}
