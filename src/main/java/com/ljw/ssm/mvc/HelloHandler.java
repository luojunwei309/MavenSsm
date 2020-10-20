package com.ljw.ssm.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssm.entity.Admin;
import com.ljw.ssm.service.api.AdminService;

// @Controller
public class HelloHandler {
	
	@Autowired
	private AdminService adminService;
	
	
	@ResponseBody
	@RequestMapping("/test/admin.html")
	public List<Admin> test(Model model) {
		List<Admin> admin=adminService.getall();
		// model.addAttribute("admin", admin);
		System.out.println("------------");
		return admin;
	}

}
