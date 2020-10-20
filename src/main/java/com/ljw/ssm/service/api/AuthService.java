package com.ljw.ssm.service.api;

import java.util.List;
import java.util.Map;

import com.ljw.ssm.entity.Auth;

public interface AuthService {

	// 查询全部权限
	List<Auth> getAll();

	// 查询角色Id查找全部权限
	List<Integer> getRoleIdAuth(Integer roleId);

	 // 保存角色和权限的关系
	void saveRoleAuth(Map<String, List<Integer>> map);

	// 根据adminId查找权限(auth)
	List<String> getAuthByAdminId(Integer adminId);

}
