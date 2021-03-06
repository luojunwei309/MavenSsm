package com.ljw.ssm.mapper;

import com.ljw.ssm.entity.ProductImage;
import com.ljw.ssm.entity.ProductImageExample;
import com.ljw.ssm.mvc.handler.ProductIma;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProductImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    int insert(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    int insertSelective(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    List<ProductImage> selectByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    ProductImage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productimage
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProductImage record);

 // 查找产品的图片
	List<ProductIma> selectAllImaInPid(@Param("pid")Integer pid);

	// 保存单个图片
	void saveIma(@Param("pid")Integer pid, @Param("type")String type);
}