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
	
	// �����ɫ��Ȩ�޵Ĺ�ϵ
	@ResponseBody
	@RequestMapping("/assign/do/role/assign/auth.json")
	public ResultEntity<String> saveRoleAuth(
			@RequestBody Map<String, List<Integer>> map){
		 authService.saveRoleAuth(map);
		return ResultEntity.successWithoutData();
	}
	
	// ��ѯ��ɫId����ȫ��Ȩ��
	@ResponseBody
	@RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
	public ResultEntity<List<Integer>> getRoleIdAuth(@RequestParam("roleId")Integer roleId){
		List<Integer> authList=authService.getRoleIdAuth(roleId);
		
		return ResultEntity.successWithData(authList);
	}
	
	// ��ѯȫ��Ȩ��
	@ResponseBody
	@RequestMapping("/assign/get/all/auth.json")
	public ResultEntity<List<Auth>> getAllAuth(){
		List<Auth> authList=authService.getAll();
		return ResultEntity.successWithData(authList);
	}
	
	// ����admin�µĽ�ɫ
	@RequestMapping("/assign/do/role/assign.html")
	public String saveAdminRoleRelationship(@RequestParam("adminId")Integer adminId,
			@RequestParam("pageNum")Integer pageNum,
			@RequestParam(value = "keyword",defaultValue = "")String keyword,
			@RequestParam(value="roleIdList",required = false)List<Integer> roleIdList) {
		
		adminService.saveAdminRole(adminId,roleIdList);
		
		return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
	}

	// �����û��ͽ�ɫ�Ĺ���
	@RequestMapping("/assign/to/assign/role/page.html")
	public String adminRole(@RequestParam("adminId")Integer adminId,Model model) {
		// ?adminId=${admin.id }&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}
		
		// ���Ҹ����adminId�����Ľ�ɫ
		List<Role> Allocated=roleService.getAllocated(adminId);
		model.addAttribute("Allocated", Allocated);
		
		// ���Ҹ����adminIdû�й����Ľ�ɫ
		List<Role> Undestrbute=roleService.getUndestrbute(adminId);
		model.addAttribute("Undestrbute", Undestrbute);
		
		return "assign-role";
	}
}
