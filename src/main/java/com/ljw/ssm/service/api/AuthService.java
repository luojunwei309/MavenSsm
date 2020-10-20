package com.ljw.ssm.service.api;

import java.util.List;
import java.util.Map;

import com.ljw.ssm.entity.Auth;

public interface AuthService {

	// ��ѯȫ��Ȩ��
	List<Auth> getAll();

	// ��ѯ��ɫId����ȫ��Ȩ��
	List<Integer> getRoleIdAuth(Integer roleId);

	 // �����ɫ��Ȩ�޵Ĺ�ϵ
	void saveRoleAuth(Map<String, List<Integer>> map);

	// ����adminId����Ȩ��(auth)
	List<String> getAuthByAdminId(Integer adminId);

}
