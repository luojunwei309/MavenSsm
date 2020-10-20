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
		System.out.println("ǰ��֪ͨ");
	}

	@AfterReturning("aa()")
	public void after(JoinPoint joinPoint) {
		System.out.println("����֪ͨ"+Arrays.asList(joinPoint.getArgs()));
	}
	
	@Around("aa()")
	public Object a(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("���Ƶ���ǰ");
		String name = joinPoint.getTarget().getClass().getName();
		String name2 = joinPoint.getSignature().getName();
		Object proceed = joinPoint.proceed();
		System.out.println("���Ƶ��ú�;������:"+name+",������:"+name2);
		return proceed;
	}
}
