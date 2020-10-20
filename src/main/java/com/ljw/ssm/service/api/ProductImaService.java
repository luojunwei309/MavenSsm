package com.ljw.ssm.service.api;

import java.util.List;

import com.ljw.ssm.entity.ProductImages;
import com.ljw.ssm.mvc.handler.ProductIma;

public interface ProductImaService {

	// ���Ҳ�Ʒ��ͼƬ
	List<ProductIma> selectAllIma(Integer pid);

	// ���浥��ͼƬ
	void saveIma(Integer pid, String type);

	// ɾ��
	void imaDel(Integer id);

	// ��������ͼƬ
	void saveImages(Integer pid, String type);

	// ��������ͼƬ
	List<ProductImages> selectAllImages(Integer pid);

	void imagesDel(Integer id);

}
