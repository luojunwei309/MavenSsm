package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Category;

public interface CategoryService {

	// �������
	void saveCategory(String pictname, String name);

	// ��ѯ���࣬������
	PageInfo<Category> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

	// ɾ������
	void Del(Integer id);

	// ����Id����category
	Category selectCategory(Integer id);

	// �޸�
	void update(Integer id,String name, String pictname);

	// ɾ�����
	void remove(int array);


}
