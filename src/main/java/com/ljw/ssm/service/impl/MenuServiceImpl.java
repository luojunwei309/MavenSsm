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

	// ����Menu������װ����
	@Override
	public Menu getTree() {
		List<Menu> menuList = menuMapper.selectByExample(new MenuExample());
		Menu root=null;
		HashMap<Integer, Menu> menMap = new HashMap<Integer, Menu>();
		
		// ����menuList,��id��Ϊ����
		for (Menu menu : menuList) {
			Integer id = menu.getId();
			menMap.put(id, menu);
		}
		
		// �ٴα���menuLisr,���Ҹ��ڵ�
		for (Menu menu : menuList) {
			Integer pid = menu.getPid();
			// Ϊ�����Ǹ��ڵ㣬��ֹͣ�ж�
			if (pid == null) {
				root=menu;
				continue;
			}
			
			// ��Ϊ��
			Menu father = menMap.get(pid);
			
			father.getChildren().add(menu);
		}
		
		return root;
	}
}
