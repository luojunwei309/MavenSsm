package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.User;

public interface UserService {

	// �û��˺Ų�ѯ������
	PageInfo<User> getPage(String keyword, Integer pageNum, Integer pageSize);

}
