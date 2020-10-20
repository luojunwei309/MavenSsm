package com.ljw.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Category;
import com.ljw.ssm.mapper.CategoryMapper;
import com.ljw.ssm.service.api.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryMapper categoryMapper;

	// �������
	@Override
	public void saveCategory(String pictname, String name) {
		categoryMapper.saveCategory(pictname,name);
		System.out.println(pictname);
		System.out.println(name);
	}

	// ��ѯ���࣬������
	@Override
	public PageInfo<Category> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Category> list=categoryMapper.selectByKeyword(keyword);
		return new PageInfo<Category>(list);
	}

	// ɾ������
	@Override
	public void Del(Integer id) {
		categoryMapper.deleteByPrimaryKey(id);
		
	}

	// ����Id����category
	@Override
	public Category selectCategory(Integer id) {
		Category c = categoryMapper.selectByPrimaryKey(id);
		return c;
	}


    // �޸�
	@Override
	public void update(Integer id,String name, String pictname) {
		categoryMapper.updatebyKey(id,name,pictname);
		
	}

	// ɾ�����
	@Override
	public void remove(int array) {
			categoryMapper.deleteByPrimaryKey(array);
		
	}



}
