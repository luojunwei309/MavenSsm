package com.ljw.ssm.mvc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ljw.ssm.entity.Admin;
import com.ljw.ssm.entity.Role;
import com.ljw.ssm.service.api.AdminService;
import com.ljw.ssm.service.api.AuthService;
import com.ljw.ssm.service.api.RoleService;

@Component
public class CrowdUserDetailsService implements UserDetailsService{

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 先根据账号查找Admin对象（username传过来的是账号）
		Admin admin = adminService.getAdminByLoginAcct(username);
		
		// 获取adminId
		Integer adminId = admin.getId();
		
		// 根据adminId查找所拥有的的角色(role)
		List<Role> allocated = roleService.getAllocated(adminId);
		
		// 根据adminId查找权限(auth)
		List<String> authNameList =authService.getAuthByAdminId(adminId);
		
		// 拿来存储权限信息(GrantedAuthority)
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// 遍历所拥有的角色(role)
		for (Role role : allocated) {
			// 角色需要加"ROLE_"
			String roleName = "ROLE_"+role.getName();
		    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
		    authorities.add(simpleGrantedAuthority);
		}
		
		// 遍历所拥有的权限(auth)
		for (String authName : authNameList) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
			authorities.add(simpleGrantedAuthority);
		}
		
		SercurityAdmin sercurityAdmin = new SercurityAdmin(admin, authorities);
		
		
		return sercurityAdmin;
	}

}
