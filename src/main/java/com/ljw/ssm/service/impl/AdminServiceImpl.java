package com.ljw.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Admin;
import com.ljw.ssm.entity.AdminExample;
import com.ljw.ssm.entity.AdminExample.Criteria;
import com.ljw.ssm.exception.InsertAdminException;
import com.ljw.ssm.exception.LoginException;
import com.ljw.ssm.exception.updateAdminAcctException;
import com.ljw.ssm.mapper.AdminMapper;
import com.ljw.ssm.service.api.AdminService;
import com.ljw.ssm.util.CrowdConstand;
import com.ljw.ssm.util.CrowdUtil;

@Service
public class AdminServiceImpl implements AdminService{
	
	Logger logger=LoggerFactory.getLogger(AdminServiceImpl.class);
	

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<Admin> getall() {
		List<Admin> selectByExample = adminMapper.selectByExample(new AdminExample());
		return selectByExample;
	}


	// 用户登录
	@Override
	public Admin getByAdminLogin(String loginAcct, String userPswd) {
		AdminExample adminExample = new AdminExample();
		Criteria createCriteria = adminExample.createCriteria();
		createCriteria.andLoginAcctEqualTo(loginAcct);
		List<Admin> list = adminMapper.selectByExample(adminExample);
		logger.debug(list.toString());
		if (list==null || list.size()==0) {
			throw new LoginException(CrowdConstand.MESSAGE_LOGIN_FAILED);
		}
		if (list.size()>1) {
			throw new RuntimeException(CrowdConstand.ADMIN_COUNT);
		}
		
		Admin admin = list.get(0);
		if (admin==null) {
			throw new LoginException(CrowdConstand.ADMIN_NULL);
		}
		
		String userName = admin.getUserPswd();
		
		String userPswdF = CrowdUtil.md5(userPswd);
		
		if (!Objects.equals(userPswdF, userName)) {
			throw new LoginException(CrowdConstand.MESSAGE_LOGIN_FAILED);
		}
		logger.debug(userName+"-"+userPswd+"-"+userPswdF+"-"+admin);

		
		
		return admin;
	}


	// Admin分页
	@Override
	public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Admin> admin=adminMapper.selectAdminByKeyword(keyword);
		return new PageInfo<Admin>(admin);
	}


	// 增加用户
	@Override
	public void saveAdmin(Admin admin) {
		//  加密
		String userPsed=admin.getUserPswd();
		// userPsed = CrowdUtil.md5(userPsed);
		userPsed = bCryptPasswordEncoder.encode(userPsed);
		admin.setUserPswd(userPsed);
		
		// 创建时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = simpleDateFormat.format(date);
		admin.setCreateTime(format);
		
		// 保存
		try {
			adminMapper.insert(admin);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("异常类名:"+e.getClass().getName());
			if (e instanceof DuplicateKeyException) {
				throw new InsertAdminException(CrowdConstand.MESSAGE_ADMIN_INSERT);
			}
		}
		
		
	}


	// 更新（去更新页面
	@Override
	public Admin getAdminById(Integer adminId) {
		
		return adminMapper.selectByPrimaryKey(adminId);
	}


	// 更新
	@Override
	public void updateAdmin(Admin admin) {
		try {
			// 选择性更新
			adminMapper.updateByPrimaryKeySelective(admin);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("异常:"+e.getClass().getName());
			if (e instanceof DuplicateKeyException) {
				throw new updateAdminAcctException(CrowdConstand.MESSAGE_ADMIN);
			}
		}
		
	}

    // 删除
	@Override
	public void remove(Integer adminId) {
		adminMapper.deleteByPrimaryKey(adminId);
		
	}


	// 保存admin新的角色
	@Override
	public void saveAdminRole(Integer adminId, List<Integer> roleIdList) {
		// 先删除admin的角色关系
		adminMapper.deleteOldRole(adminId);
		
		// 在保存新的关系
		if (roleIdList != null && roleIdList.size()>0) {
			adminMapper.inserNewRole(adminId,roleIdList);
		}
	}


	// 根据账号名称查询Admin对象
	@Override
	public Admin getAdminByLoginAcct(String username) {
		AdminExample adminExample = new AdminExample();
		Criteria createCriteria = adminExample.createCriteria();
		createCriteria.andLoginAcctEqualTo(username);
		 List<Admin> selectByExample = adminMapper.selectByExample(adminExample);
		 Admin admin = selectByExample.get(0);
		 return admin;
	}
	

}
