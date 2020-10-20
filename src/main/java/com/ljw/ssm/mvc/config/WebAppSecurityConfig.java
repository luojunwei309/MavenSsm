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
	       // 临时
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
	    authorizeRequests()  // 对请求进行授权
	    .antMatchers("/admin/to/login/page.html")  // 针对这个路径进行
	    .permitAll()  // 无条件访问
	    .antMatchers("/crowd/**","/bootstrap/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**")
	    .permitAll()
//	    .antMatchers("/admin/get/page.html")
//	    .access("hasRole('经理')")
	    //.antMatchers("/admin/get/page.html")  // 针对分页请求设置访问控制
		//.access("hasRole('经理') OR hasAuthority('user:get')")  // 需要经理角色
//		.antMatchers("")  
//		.hasAuthority("")   设置权限
//		.antMatchers("")
//		.access("(hasRole('') and hasAuthorty('') or (hasRole('') and hasAuthorty('') )")
	    .anyRequest()				 // 任意的请求
		.authenticated()             // 需要登录以后才能访问
		.and ()
		.exceptionHandling()   // 开启异常处理
		.accessDeniedHandler(new AccessDeniedHandler() {   // 发生异常的时候带消息去这个地址
			
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				  request.setAttribute("exception", new Exception(CrowdConstand.MESSAGE_ACCESS_DENIED));
				  request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
			}  
		}) 
		.and()
		.csrf()  // 防跨站请求伪造功能
		.disable()  // 禁用
		.formLogin()  // 开启表单登陆功能
		.loginPage("/admin/to/login/page.html") // 指定登陆页面
		.loginProcessingUrl("/security/do/login.html")   // 处理登录请求地址
		.defaultSuccessUrl("/admin/to/main/page.html")  // 指定登录成功去往的地址
		.usernameParameter("loginAcct")
		.passwordParameter("userPswd")
		.and()
		.logout()  // 开启退出登陆功能
		.logoutUrl("/security/do/logout.html")   // 指定退出登陆地址
		.logoutSuccessUrl("/admin/to/login/page.html")  // 指定退出以后前往的地址
		;    
	}
	
}
