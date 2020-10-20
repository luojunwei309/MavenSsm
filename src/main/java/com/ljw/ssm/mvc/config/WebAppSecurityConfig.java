package com.ljw.ssm.mvc.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.ljw.ssm.util.CrowdConstand;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       // ��ʱ
//		auth
//			    .inMemoryAuthentication()
//			    .withUser("tom")
//			    .password("123456")
//			    .roles("ADMIN");
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.
	    authorizeRequests()  // �����������Ȩ
	    .antMatchers("/admin/to/login/page.html")  // ������·������
	    .permitAll()  // ����������
	    .antMatchers("/crowd/**","/bootstrap/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**")
	    .permitAll()
//	    .antMatchers("/admin/get/page.html")
//	    .access("hasRole('����')")
	    //.antMatchers("/admin/get/page.html")  // ��Է�ҳ�������÷��ʿ���
		//.access("hasRole('����') OR hasAuthority('user:get')")  // ��Ҫ�����ɫ
//		.antMatchers("")  
//		.hasAuthority("")   ����Ȩ��
//		.antMatchers("")
//		.access("(hasRole('') and hasAuthorty('') or (hasRole('') and hasAuthorty('') )")
	    .anyRequest()				 // ���������
		.authenticated()             // ��Ҫ��¼�Ժ���ܷ���
		.and ()
		.exceptionHandling()   // �����쳣����
		.accessDeniedHandler(new AccessDeniedHandler() {   // �����쳣��ʱ�����Ϣȥ�����ַ
			
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				  request.setAttribute("exception", new Exception(CrowdConstand.MESSAGE_ACCESS_DENIED));
				  request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
			}  
		}) 
		.and()
		.csrf()  // ����վ����α�칦��
		.disable()  // ����
		.formLogin()  // ��������½����
		.loginPage("/admin/to/login/page.html") // ָ����½ҳ��
		.loginProcessingUrl("/security/do/login.html")   // �����¼�����ַ
		.defaultSuccessUrl("/admin/to/main/page.html")  // ָ����¼�ɹ�ȥ���ĵ�ַ
		.usernameParameter("loginAcct")
		.passwordParameter("userPswd")
		.and()
		.logout()  // �����˳���½����
		.logoutUrl("/security/do/logout.html")   // ָ���˳���½��ַ
		.logoutSuccessUrl("/admin/to/login/page.html")  // ָ���˳��Ժ�ǰ���ĵ�ַ
		;    
	}
	
}
