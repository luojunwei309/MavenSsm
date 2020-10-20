package com.ljw.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Product;
import com.ljw.ssm.mapper.ProductMapper;
import com.ljw.ssm.service.api.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductMapper productMapper;

	// 查询某个分类下的所有分类并分页
	@Override
	public PageInfo<Product> productAllPageGet(Integer id, String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> list=productMapper.productAllPageGet(id,keyword);
		
		return new PageInfo<Product>(list);
	}

	// 添加灿产品
	@Override
	public void save(String name, String subTitle, Float originalPrice, Float promotePrice, Integer stock, Integer cid,
			String pictname) {
//		Product product = new Product();
//	    product.setCid(cid);
//	   
//	    product.setCreateDate(date);
//	    product.setName(name);
//	    product.setOriginalPrice(originalPrice);
//	    product.setPictName(pictname);
//	    product.setPromotePrice(promotePrice);
//	    product.setStock(stock);
//	    product.setSubTitle(subTitle);
		 Date date = new Date();
	    productMapper.saveProduct(name,subTitle,originalPrice,promotePrice,stock,cid,pictname,date);
	    
		
	}

	// 删除
	@Override
	public void delProduct(Integer iid) {
		try {
			productMapper.deleteByPrimaryKey(iid);
			logger.debug("--------------del---------------SUCCESS");
		} catch (Exception e) {
			logger.debug("--------------del---------------FAILED");
		}
	}

	// 去修改页面
	@Override
	public Product selectProduct(Integer id) {
		
		return productMapper.selectByPrimaryKey(id);
	}

	// 更新
	@Override
	public void update(String name, String subTitle, Float originalPrice, Float promotePrice, Integer stock,
			Integer cid, String pictname,Integer id) {
		productMapper.updateProduct(name,subTitle,originalPrice,promotePrice,stock,cid,pictname,id);
	}
}
