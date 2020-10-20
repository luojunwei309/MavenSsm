package com.ljw.ssm.mvc.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.User;
import com.ljw.ssm.service.api.UserService;
import com.ljw.ssm.util.CrowdConstand;
import com.ljw.ssm.util.CrowdUtil;
import com.ljw.ssm.util.ResultEntity;

@Controller
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/admin/get/category.html")
	public String adminGetPage(@RequestParam(value = "keyword",defaultValue = "") String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
			Model model) {
		
		PageInfo<User> pagelist=userService.getPage(keyword,pageNum,pageSize);
		model.addAttribute("pagelist", pagelist);
		
		return "admin-user";
	}
	


}
