package com.ljw.ssm.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ljw.ssm.exception.LoginException;
import com.ljw.ssm.exception.aaa;

@Component
public class B {
	
	@Transactional(readOnly = true,isolation = Isolation.REPEATABLE_READ)
	public void add(int a,int b) {
		int c=a+b;
		System.out.println("add:"+c);
	}

}
