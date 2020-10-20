package com.ljw.ssm.service.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Property;
import com.ljw.ssm.entity.PropertyValue;

public interface PropertyService {

	// 根据分类id查找属性表
	PageInfo<Property> getCid(Integer cid, String keyword, Integer pageNum, Integer pageSize);

	// 添加属性
	void propertyAdd(Integer cid, String name);

	//  查找属性
	Property propertySelect(Integer id);

	// 更新
	void update(Property property);

	// 删除
	void del(Integer id);

	// 根据产品id查询属性值
	List<PropertyValue> propertyGet(Integer pid);

	// 根据分类id查找属性
	List<Property> getPropertyName(Integer cid);

	// 修改或者添加属性值
	void AddAndEdit(Integer pid, Integer ptid,String value);

}
