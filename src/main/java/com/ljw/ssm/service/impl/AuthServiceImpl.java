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

	// ��ѯȫ��Ȩ��
	@Override
	public List<Auth> getAll() {
		return authMapper.selectByExample(new AuthExample());
	}

	// ��ѯ��ɫId����ȫ��Ȩ��
	@Override
	public List<Integer> getRoleIdAuth(Integer roleId) {
		return authMapper.selectRidAuth(roleId);
	}

	// �����ɫ��Ȩ�޵Ĺ�ϵ
	@Override
	public void saveRoleAuth(Map<String, List<Integer>> map) {
		// �Ȼ�ȡroleId
		List<Integer> roleIdList = map.get("roleId");
		Integer roleId = roleIdList.get(0);
		
		// ɾ���ɵĽ�ɫȨ��
		authMapper.deleteOld(roleId);
		
		// ��ȡauthIdArray����Id�ļ���
		List<Integer> authIdArray = map.get("authIdArray");
		// �ж��Ƿ���Ч
		if (authIdArray != null && authIdArray.size() > 0) {
			authMapper.insertNew(roleId, authIdArray);
		}
		
		
	}

	// ����adminId����Ȩ��(auth)
	@Override
	public List<String> getAuthByAdminId(Integer adminId) {
		return authMapper.selectAuthByAdminId(adminId);
	}

}
