package com.ljw.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssm.entity.Auth;
import com.ljw.ssm.entity.AuthExample;
import com.ljw.ssm.mapper.AuthMapper;
import com.ljw.ssm.service.api.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthMapper authMapper;

	// 查询全部权限
	@Override
	public List<Auth> getAll() {
		return authMapper.selectByExample(new AuthExample());
	}

	// 查询角色Id查找全部权限
	@Override
	public List<Integer> getRoleIdAuth(Integer roleId) {
		return authMapper.selectRidAuth(roleId);
	}

	// 保存角色和权限的关系
	@Override
	public void saveRoleAuth(Map<String, List<Integer>> map) {
		// 先获取roleId
		List<Integer> roleIdList = map.get("roleId");
		Integer roleId = roleIdList.get(0);
		
		// 删除旧的角色权限
		authMapper.deleteOld(roleId);
		
		// 获取authIdArray对象Id的集合
		List<Integer> authIdArray = map.get("authIdArray");
		// 判断是否有效
		if (authIdArray != null && authIdArray.size() > 0) {
			authMapper.insertNew(roleId, authIdArray);
		}
		
		
	}

	// 根据adminId查找权限(auth)
	@Override
	public List<String> getAuthByAdminId(Integer adminId) {
		return authMapper.selectAuthByAdminId(adminId);
	}

}
