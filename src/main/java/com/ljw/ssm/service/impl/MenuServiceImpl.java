package com.ljw.ssm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssm.entity.Menu;
import com.ljw.ssm.entity.MenuExample;
import com.ljw.ssm.mapper.MenuMapper;
import com.ljw.ssm.service.api.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	// 查找Menu对象，组装成数
	@Override
	public Menu getTree() {
		List<Menu> menuList = menuMapper.selectByExample(new MenuExample());
		Menu root=null;
		HashMap<Integer, Menu> menMap = new HashMap<Integer, Menu>();
		
		// 遍历menuList,把id作为索引
		for (Menu menu : menuList) {
			Integer id = menu.getId();
			menMap.put(id, menu);
		}
		
		// 再次遍历menuLisr,查找根节点
		for (Menu menu : menuList) {
			Integer pid = menu.getPid();
			// 为空则是根节点，则停止判断
			if (pid == null) {
				root=menu;
				continue;
			}
			
			// 不为空
			Menu father = menMap.get(pid);
			
			father.getChildren().add(menu);
		}
		
		return root;
	}
}
