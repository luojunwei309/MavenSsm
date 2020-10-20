package com.ljw.ssm.mvc.inerceptor;

import java.security.AccessControlException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ljw.ssm.entity.Admin;
import com.ljw.ssm.util.CrowdConstand;

/**
 * µÇÂ½À¹½ØÆ÷
 * @author ÓÀÎÞBUG(LJW)
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		Admin admin = (Admin) session.getAttribute(CrowdConstand.ATTP_NAME_LOGIN_ADMIN);
		
		if (admin==null) {
			throw new AccessControlException(CrowdConstand.MESSAGE_ACCESS_LOGIN);
		}
		
		return true;
	}

}
