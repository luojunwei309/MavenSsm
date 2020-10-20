package com.ljw.ssm.mvc.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljw.ssm.entity.Menu;
import com.ljw.ssm.service.api.MenuService;
import com.ljw.ssm.util.ResultEntity;

@RestController
public class MenuHandler {
	@Autowired
	private MenuService menuService;
	
	
	// 查找Menu对象，组装成数
	@RequestMapping("/menu/get/whole/tree.json")
	public ResultEntity<Menu> getTree(){
		Menu menu=menuService.getTree();
		return ResultEntity.successWithData(menu);
	}

}
