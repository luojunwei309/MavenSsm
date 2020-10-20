package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Product;

public interface ProductService {

	// 查询某个分类下的所有分类并分页
	PageInfo<Product> productAllPageGet(Integer id, String keyword, Integer pageNum, Integer pageSize);

	// 添加灿产品
	void save(String name, String subTitle, Float originalPrice, Float promotePrice, Integer stock, Integer cid,
			String pictname);

	// 删除
	void delProduct(Integer iid);

	// 去修改页面
	Product selectProduct(Integer id);

	// 更新
	void update(String name, String subTitle, Float originalPrice, Float promotePrice, Integer stock, Integer cid,
			String pictname, Integer id);

}
