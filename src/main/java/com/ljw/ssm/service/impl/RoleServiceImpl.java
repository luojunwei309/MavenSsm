package com.ljw.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Role;
import com.ljw.ssm.entity.RoleExample;
import com.ljw.ssm.entity.RoleExample.Criteria;
import com.ljw.ssm.mapper.RoleMapper;
import com.ljw.ssm.service.api.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoleMapper roleMapper;

	// 角色分页
	@Override
	public PageInfo<Role> getpageInfo(String keyword, Integer pageNum, Integer pageSize) {
		
		PageHelper.startPage(pageNum,pageSize);
		// 查询
		List<Role> selectRoleByKeyword = roleMapper.selectRoleByKeyword(keyword);
		logger.info(selectRoleByKeyword.toString());
		
		return new PageInfo<Role>(selectRoleByKeyword);
	}

	// 新增角色
	@Override
	public void saveRole(Role role) {
		roleMapper.insert(role);
	}

	// 修改角色
	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}

	// 删除角色
	@Override
	public void removeRole(List<Integer> roleIdList) {
       RoleExample roleExample = new RoleExample();
       Criteria createCriteria = roleExample.createCriteria();
       createCriteria.andIdIn(roleIdList);
       roleMapper.deleteByExample(roleExample);
	}

	// 查找跟这个adminId关联的角色
	@Override
	public List<Role> getAllocated(Integer adminId) {
		
		return roleMapper.getAllocated(adminId);
	}

	// 查找跟这个adminId没有关联的角色
	@Override
	public List<Role> getUndestrbute(Integer adminId) {
		return roleMapper.getUndestrbuted(adminId);
	}
}
