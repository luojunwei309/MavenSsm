package com.ljw.ssm.service.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Property;
import com.ljw.ssm.entity.PropertyValue;

public interface PropertyService {

	// ���ݷ���id�������Ա�
	PageInfo<Property> getCid(Integer cid, String keyword, Integer pageNum, Integer pageSize);

	// �������
	void propertyAdd(Integer cid, String name);

	//  ��������
	Property propertySelect(Integer id);

	// ����
	void update(Property property);

	// ɾ��
	void del(Integer id);

	// ���ݲ�Ʒid��ѯ����ֵ
	List<PropertyValue> propertyGet(Integer pid);

	// ���ݷ���id��������
	List<Property> getPropertyName(Integer cid);

	// �޸Ļ����������ֵ
	void AddAndEdit(Integer pid, Integer ptid,String value);

}
