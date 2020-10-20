package com.ljw.ssm.mvc.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.params.AllClientPNames;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ljw.ssm.exception.InsertAdminException;
import com.ljw.ssm.exception.LoginException;
import com.ljw.ssm.exception.aaa;
import com.ljw.ssm.exception.updateAdminAcctException;
import com.ljw.ssm.util.CrowdConstand;
import com.ljw.ssm.util.CrowdUtil;
import com.ljw.ssm.util.ResultEntity;

/**
 * 基于注解的异常处理器
 * @author 永无BUG(LJW)
 *
 */
@ControllerAdvice
public class CrowdExceptionResplver {
	
	// 更新admin时候的异常
		@ExceptionHandler(value = updateAdminAcctException.class)
		public ModelAndView resolveupdateAdminAcctException(
				updateAdminAcctException exception,
				HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			String viewName ="system-error";
			 
			//11、返回ModelAndView对象
			return commonResolve(viewName, exception, request, response);
		}
	
	// 访问被拒接的
		@ExceptionHandler(value = Exception.class)
		public ModelAndView resolveException(Exception exception,HttpServletRequest request,HttpServletResponse response) throws IOException {
			String viewName="system-error";
			 
			//11、返回ModelAndView对象
			return commonResolve(viewName, exception, request, response);
		}
	
	/**
	 * 保存Admin异常
	 * @param exception
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(value = InsertAdminException.class)
	public ModelAndView InsertAdminException(InsertAdminException exception,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String viewName="admin-add";
		return commonResolve(viewName, exception, request, response);
		
	}
	
	
	@ExceptionHandler(value = aaa.class)
	public ModelAndView All(aaa exception,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String viewName="system-error";
		
		return commonResolve(viewName, exception, request, response);
		
	}
	
	/**
	 * 登陆失败的异常
	 * @param exception
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(value = LoginException.class)
	public ModelAndView LoginException(
			LoginException exception,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String viewName="admin_login";
		return commonResolve(viewName, exception, request, response);
	}
	
	
	private ModelAndView commonResolve(String viewName,Exception exception,HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 1.判断当前请求类型
		boolean ajaxRequestType = CrowdUtil.ajaxRequestType(request);
		// 2.如果是ajax请求
		if (ajaxRequestType) {
			// 创建RedultEntity对象
			ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
		    // 创建GSON对象
			Gson gson=new Gson();
			String json = gson.toJson(failed);
			response.getWriter().write(json);
			return null;
		}
		// 3.如果不是ajax请求
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(CrowdConstand.EXCEPTION, exception);
		modelAndView.setViewName(viewName);
		return modelAndView;
		
		
	}

}
