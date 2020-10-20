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

	// ���ݷ���id�������Ա�
	@Override
	public PageInfo<Property> getCid(Integer cid, String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Property> list=propertyMapper.selectByKeyGetCid(cid,keyword);
		return new PageInfo<Property>(list);
	}

	// �������
	@Override
	public void propertyAdd(Integer cid, String name) {
		if (cid == null && name == null) {
			throw new aaa("�����ֵΪ��");
		}
		logger.debug("��ӳɹ�");
		propertyMapper.insertProperty(cid,name);
		
	}

	// ��������
	@Override
	public Property propertySelect(Integer id) {
		Property property = propertyMapper.selectByPrimaryKey(id);
		logger.debug("���ҳɹ�:"+property);
		return property;
	}

	// ����
	@Override
	public void update(Property property) {
		logger.debug("���³ɹ�");
		propertyMapper.updateByPrimaryKey(property);
		
	}

	// ɾ��
	@Override
	public void del(Integer id) {
		if (id == null) {
			logger.debug("id:"+id.toString());
			throw new aaa("����ֵidΪ��");
		}
		propertyMapper.deleteByPrimaryKey(id);
	}

	// ���ݲ�Ʒid��ѯ����ֵ
	@Override
	public List<PropertyValue> propertyGet(Integer pid) {
		List<PropertyValue> list=propertyValueMapper.getPid(pid);
		return list;
	}

	// ���ݷ���id��������
	@Override
	public List<Property> getPropertyName(Integer cid) {
		
		List<Property> list=propertyMapper.selectByPropertyName(cid);
		return list;
	}

	// �޸Ļ����������ֵ
	@Override
	public void AddAndEdit(Integer pid, Integer ptid, String value) {
		propertyValueMapper.AddAndEdit(pid,ptid,value);
	}
}
