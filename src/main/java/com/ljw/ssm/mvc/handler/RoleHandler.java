package com.ljw.ssm.mvc.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Role;
import com.ljw.ssm.service.api.RoleService;
import com.ljw.ssm.util.ResultEntity;

@RestController
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping("/test/getRole.json")
	public ResultEntity<PageInfo<Role>> test(@RequestParam(value = "keyword",defaultValue = "")String keyword) {
		PageInfo<Role> getpageInfo = roleService.getpageInfo(keyword, 1, 5);
		return ResultEntity.successWithData(getpageInfo);
	}
	
	// 删除角色
	@RequestMapping("/role/delete/by/role/id/array.json")
	public ResultEntity<String> removeRole(@RequestBody List<Integer> roleIdList){
		roleService.removeRole(roleIdList);
		return ResultEntity.successWithoutData();
	}
	
	// 修改角色
	@RequestMapping("/role/update.json")
	public ResultEntity<String> updateRole(Role role){
		roleService.updateRole(role);
		
		return ResultEntity.successWithoutData();
	}
	
	// 新增角色
	@RequestMapping("/role.json")
	public ResultEntity<String> saveRole(Role role){
		roleService.saveRole(role);
		return ResultEntity.successWithoutData();
	}
	
	
	// 角色分页
	 @RequestMapping("/role/get/page/info.json")
	public ResultEntity<PageInfo<Role>> getpageInfo(
			@RequestParam(value = "keyword",defaultValue = "")String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
		 
		PageInfo<Role> pageInfo =roleService.getpageInfo(keyword,pageNum,pageSize);
		
		logger.info("keyword:"+keyword+";pageNum:"+pageNum+";pageSize:"+pageSize+";pageInfo:"+pageInfo);
		 return ResultEntity.successWithData(pageInfo);
	 }
}
