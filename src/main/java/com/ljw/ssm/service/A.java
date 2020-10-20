package com.ljw.ssm.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class A {
	
	@Pointcut("execution(* com.ljw.ssm.service.*.*(..))")
	public void aa() {}
	
	@Before("aa()")
	public void Before() {
		System.out.println("前置通知");
	}

	@AfterReturning("aa()")
	public void after(JoinPoint joinPoint) {
		System.out.println("后置通知"+Arrays.asList(joinPoint.getArgs()));
	}
	
	@Around("aa()")
	public Object a(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("环绕调用前");
		String name = joinPoint.getTarget().getClass().getName();
		String name2 = joinPoint.getSignature().getName();
		Object proceed = joinPoint.proceed();
		System.out.println("环绕调用后;类型名:"+name+",方法名:"+name2);
		return proceed;
	}
}
