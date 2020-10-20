package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Category;

public interface CategoryService {

	// 保存分类
	void saveCategory(String pictname, String name);

	// 查询分类，并整理
	PageInfo<Category> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

	// 删除分类
	void Del(Integer id);

	// 根据Id查找category
	Category selectCategory(Integer id);

	// 修改
	void update(Integer id,String name, String pictname);

	// 删除多个
	void remove(int array);


}
