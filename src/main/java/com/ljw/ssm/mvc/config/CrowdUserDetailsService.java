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
		// �ȸ����˺Ų���Admin����username�����������˺ţ�
		Admin admin = adminService.getAdminByLoginAcct(username);
		
		// ��ȡadminId
		Integer adminId = admin.getId();
		
		// ����adminId������ӵ�еĵĽ�ɫ(role)
		List<Role> allocated = roleService.getAllocated(adminId);
		
		// ����adminId����Ȩ��(auth)
		List<String> authNameList =authService.getAuthByAdminId(adminId);
		
		// �����洢Ȩ����Ϣ(GrantedAuthority)
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// ������ӵ�еĽ�ɫ(role)
		for (Role role : allocated) {
			// ��ɫ��Ҫ��"ROLE_"
			String roleName = "ROLE_"+role.getName();
		    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
		    authorities.add(simpleGrantedAuthority);
		}
		
		// ������ӵ�е�Ȩ��(auth)
		for (String authName : authNameList) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
			authorities.add(simpleGrantedAuthority);
		}
		
		SercurityAdmin sercurityAdmin = new SercurityAdmin(admin, authorities);
		
		
		return sercurityAdmin;
	}

}
