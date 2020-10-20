package com.ljw.ssm.service.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Role;

public interface RoleService {

	// 角色分页
	PageInfo<Role> getpageInfo(String keyword, Integer pageNum, Integer pageSize);

	// 新增角色
	void saveRole(Role role);

	// 修改角色
	void updateRole(Role role);

	// 删除角色
	void removeRole(List<Integer> roleIdList);

	// 查找跟这个adminId关联的角色
	List<Role> getAllocated(Integer adminId);

	// 查找跟这个adminId没有关联的角色
	List<Role> getUndestrbute(Integer adminId);
	

}
