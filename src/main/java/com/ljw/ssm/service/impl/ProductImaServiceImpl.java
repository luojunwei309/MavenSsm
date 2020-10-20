package com.ljw.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssm.entity.ProductImages;
import com.ljw.ssm.mapper.ProductImageMapper;
import com.ljw.ssm.mapper.ProductImagesMapper;
import com.ljw.ssm.mvc.handler.ProductIma;
import com.ljw.ssm.service.api.ProductImaService;

@Service
public class ProductImaServiceImpl implements ProductImaService{
	
	@Autowired
	private ProductImageMapper productImageMapper;
	@Autowired
	private ProductImagesMapper productImagesMapper;

	// ���Ҳ�Ʒ��ͼƬ
	@Override
	public List<ProductIma> selectAllIma(Integer pid) {
		
		
		return productImageMapper.selectAllImaInPid(pid);
	}

	// ���浥��ͼƬ
	@Override
	public void saveIma(Integer pid, String type) {
		productImageMapper.saveIma(pid,type);
	}

	// ɾ��
	@Override
	public void imaDel(Integer id) {
		productImageMapper.deleteByPrimaryKey(id);
		
	}

	// ��������ͼƬ
	@Override
	public void saveImages(Integer pid, String type) {
		productImagesMapper.saveImages(pid,type);
	}

	// ��������ͼƬ
	@Override
	public List<ProductImages> selectAllImages(Integer pid) {
		return productImagesMapper.selectAllImages(pid);
	}

	@Override
	public void imagesDel(Integer id) {
		productImagesMapper.deleteByPrimaryKey(id);
	}

}
