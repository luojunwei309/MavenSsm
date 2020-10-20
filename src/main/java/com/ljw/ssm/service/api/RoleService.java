package com.ljw.ssm.service.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Role;

public interface RoleService {

	// ��ɫ��ҳ
	PageInfo<Role> getpageInfo(String keyword, Integer pageNum, Integer pageSize);

	// ������ɫ
	void saveRole(Role role);

	// �޸Ľ�ɫ
	void updateRole(Role role);

	// ɾ����ɫ
	void removeRole(List<Integer> roleIdList);

	// ���Ҹ����adminId�����Ľ�ɫ
	List<Role> getAllocated(Integer adminId);

	// ���Ҹ����adminIdû�й����Ľ�ɫ
	List<Role> getUndestrbute(Integer adminId);
	

}
