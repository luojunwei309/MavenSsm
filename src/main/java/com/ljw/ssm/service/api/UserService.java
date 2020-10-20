package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.User;

public interface UserService {

	// 用户账号查询并分类
	PageInfo<User> getPage(String keyword, Integer pageNum, Integer pageSize);

}
