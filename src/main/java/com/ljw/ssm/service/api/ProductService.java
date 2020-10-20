package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Product;

public interface ProductService {

	// ��ѯĳ�������µ����з��ಢ��ҳ
	PageInfo<Product> productAllPageGet(Integer id, String keyword, Integer pageNum, Integer pageSize);

	// ��ӲӲ�Ʒ
	void save(String name, String subTitle, Float originalPrice, Float promotePrice, Integer stock, Integer cid,
			String pictname);

	// ɾ��
	void delProduct(Integer iid);

	// ȥ�޸�ҳ��
	Product selectProduct(Integer id);

	// ����
	void update(String name, String subTitle, Float originalPrice, Float promotePrice, Integer stock, Integer cid,
			String pictname, Integer id);

}
