package com.ljw.ssm.service.api;

import java.util.List;

import com.ljw.ssm.entity.ProductImages;
import com.ljw.ssm.mvc.handler.ProductIma;

public interface ProductImaService {

	// 查找产品的图片
	List<ProductIma> selectAllIma(Integer pid);

	// 保存单个图片
	void saveIma(Integer pid, String type);

	// 删除
	void imaDel(Integer id);

	// 保存详情图片
	void saveImages(Integer pid, String type);

	// 查找详情图片
	List<ProductImages> selectAllImages(Integer pid);

	void imagesDel(Integer id);

}
