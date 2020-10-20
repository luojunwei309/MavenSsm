package com.ljw.ssm.mvc.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ljw.ssm.entity.Admin;

/**
 * 考虑到User对象中仅仅包含账号和密码，为了获得到原始的Admin对象，专门建这个类对User进行扩展
 * @author 永无BUG(LJW)
 *
 */
public class SercurityAdmin extends User{
	private static final long serialVersionUID = 1L;
	private Admin originaAdmin;
	
	public SercurityAdmin(Admin originaAdmin,
			List<GrantedAuthority> authorities) {
		
		super(originaAdmin.getLoginAcct(), originaAdmin.getUserPswd(), authorities);
	
	  this.originaAdmin=originaAdmin;
	  originaAdmin.setUserPswd(null);
	}
	
	
	public Admin getoriginaAdmin() {
		return originaAdmin;
	}

}
