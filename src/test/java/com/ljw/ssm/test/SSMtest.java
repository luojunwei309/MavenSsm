package com.ljw.ssm.test;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ljw.ssm.mapper.AdminMapper;
import com.ljw.ssm.mapper.RoleMapper;
import com.ljw.ssm.service.B;
import com.ljw.ssm.util.CrowdUtil;

public class SSMtest {
	

	@Autowired
	private  AdminMapper adminMapper;
	
	@Autowired
	private RoleMapper mapper;
	
	Logger logger=LoggerFactory.getLogger(SSMtest.class);

	@SuppressWarnings("resource")
	@Test
	public void test01() {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
//		String[] names = context.getBeanDefinitionNames();
//		for (String string : names) {
//			System.out.println(string);
//		}
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("spring-tx.xml");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
		String[] names = context.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
	}
	
	

	@Test
	public void test() throws SQLException {
	  @SuppressWarnings("resource")
	ApplicationContext sc=new ClassPathXmlApplicationContext("spring-mybatis.xml");
	  DataSource dataSource=sc.getBean(DataSource.class);
	  System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testMD5() {
		String source="123456";
		String md5 = CrowdUtil.md5(source);
		logger.debug(md5);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void tddd() {
		ClassPathXmlApplicationContext sc = new ClassPathXmlApplicationContext("spring-tx.xml");
//		String[] beanDefinitionNames = sc.getBeanDefinitionNames();
//		for (String string : beanDefinitionNames) {
//			System.out.println(string);
//		}
		B bean = (B) sc.getBean("b");
		bean.add(1,5);
		
	}
	
	@Test
	public void t() throws IOException {
		ClassPathXmlApplicationContext sc = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		int beanDefinitionCount = sc.getBeanDefinitionCount();
		System.out.println(beanDefinitionCount);
		
	}

}
