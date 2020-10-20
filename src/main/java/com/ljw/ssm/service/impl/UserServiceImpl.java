package com.ljw.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.User;
import com.ljw.ssm.mapper.UserMapper;
import com.ljw.ssm.service.api.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	// 用户账号查询并分类
	@Override
	public PageInfo<User> getPage(String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list=userMapper.selectUserByKey(keyword);
		return new PageInfo<User>(list);
	}

}
