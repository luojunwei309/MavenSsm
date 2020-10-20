package com.ljw.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Property;
import com.ljw.ssm.entity.PropertyValue;
import com.ljw.ssm.exception.aaa;
import com.ljw.ssm.mapper.PropertyMapper;
import com.ljw.ssm.mapper.PropertyValueMapper;
import com.ljw.ssm.mvc.handler.PropertyHandler;
import com.ljw.ssm.service.api.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService{
	
	private Logger logger=LoggerFactory.getLogger(PropertyServiceImpl.class);
	@Autowired
	private PropertyMapper propertyMapper;
	
	@Autowired
	private PropertyValueMapper propertyValueMapper;

	// 根据分类id查找属性表
	@Override
	public PageInfo<Property> getCid(Integer cid, String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Property> list=propertyMapper.selectByKeyGetCid(cid,keyword);
		return new PageInfo<Property>(list);
	}

	// 添加属性
	@Override
	public void propertyAdd(Integer cid, String name) {
		if (cid == null && name == null) {
			throw new aaa("输入的值为空");
		}
		logger.debug("添加成功");
		propertyMapper.insertProperty(cid,name);
		
	}

	// 查找属性
	@Override
	public Property propertySelect(Integer id) {
		Property property = propertyMapper.selectByPrimaryKey(id);
		logger.debug("查找成功:"+property);
		return property;
	}

	// 更新
	@Override
	public void update(Property property) {
		logger.debug("更新成功");
		propertyMapper.updateByPrimaryKey(property);
		
	}

	// 删除
	@Override
	public void del(Integer id) {
		if (id == null) {
			logger.debug("id:"+id.toString());
			throw new aaa("参数值id为空");
		}
		propertyMapper.deleteByPrimaryKey(id);
	}

	// 根据产品id查询属性值
	@Override
	public List<PropertyValue> propertyGet(Integer pid) {
		List<PropertyValue> list=propertyValueMapper.getPid(pid);
		return list;
	}

	// 根据分类id查找属性
	@Override
	public List<Property> getPropertyName(Integer cid) {
		
		List<Property> list=propertyMapper.selectByPropertyName(cid);
		return list;
	}

	// 修改或者添加属性值
	@Override
	public void AddAndEdit(Integer pid, Integer ptid, String value) {
		propertyValueMapper.AddAndEdit(pid,ptid,value);
	}
}
