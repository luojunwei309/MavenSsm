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

	// ��ɫ��ҳ
	@Override
	public PageInfo<Role> getpageInfo(String keyword, Integer pageNum, Integer pageSize) {
		
		PageHelper.startPage(pageNum,pageSize);
		// ��ѯ
		List<Role> selectRoleByKeyword = roleMapper.selectRoleByKeyword(keyword);
		logger.info(selectRoleByKeyword.toString());
		
		return new PageInfo<Role>(selectRoleByKeyword);
	}

	// ������ɫ
	@Override
	public void saveRole(Role role) {
		roleMapper.insert(role);
	}

	// �޸Ľ�ɫ
	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKey(role);
	}

	// ɾ����ɫ
	@Override
	public void removeRole(List<Integer> roleIdList) {
       RoleExample roleExample = new RoleExample();
       Criteria createCriteria = roleExample.createCriteria();
       createCriteria.andIdIn(roleIdList);
       roleMapper.deleteByExample(roleExample);
	}

	// ���Ҹ����adminId�����Ľ�ɫ
	@Override
	public List<Role> getAllocated(Integer adminId) {
		
		return roleMapper.getAllocated(adminId);
	}

	// ���Ҹ����adminIdû�й����Ľ�ɫ
	@Override
	public List<Role> getUndestrbute(Integer adminId) {
		return roleMapper.getUndestrbuted(adminId);
	}
}
