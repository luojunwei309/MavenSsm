package com.ljw.ssm.service.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Admin;

public interface AdminService {

	List<Admin> getall();

	Admin getByAdminLogin(String loginAcct, String userPswd);

	PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

	void saveAdmin(Admin admin);

	Admin getAdminById(Integer adminId);

	void updateAdmin(Admin admin);

	void remove(Integer adminId);

	// 保存admin新的角色
	void saveAdminRole(Integer adminId, List<Integer> roleIdList);

	// 根据账号名称查询Admin对象
	Admin getAdminByLoginAcct(String username);
	

}
