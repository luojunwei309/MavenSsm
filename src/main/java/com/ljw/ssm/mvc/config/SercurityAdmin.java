package com.ljw.ssm.mvc.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ljw.ssm.entity.Admin;

/**
 * ���ǵ�User�����н��������˺ź����룬Ϊ�˻�õ�ԭʼ��Admin����ר�Ž�������User������չ
 * @author ����BUG(LJW)
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
