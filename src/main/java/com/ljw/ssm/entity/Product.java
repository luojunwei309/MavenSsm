package com.ljw.ssm.entity;

import java.util.Date;

public class Product {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.subTitle
     *
     * @mbggenerated
     */
    private String subTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.originalPrice
     *
     * @mbggenerated
     */
    private Float originalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.promotePrice
     *
     * @mbggenerated
     */
    private Float promotePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.stock
     *
     * @mbggenerated
     */
    private Integer stock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.cid
     *
     * @mbggenerated
     */
    private Integer cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.createDate
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.pictName
     *
     * @mbggenerated
     */
    private String pictName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.id
     *
     * @return the value of product.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.id
     *
     * @param id the value for product.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.name
     *
     * @return the value of product.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.name
     *
     * @param name the value for product.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.subTitle
     *
     * @return the value of product.subTitle
     *
     * @mbggenerated
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.subTitle
     *
     * @param subTitle the value for product.subTitle
     *
     * @mbggenerated
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.originalPrice
     *
     * @return the value of product.originalPrice
     *
     * @mbggenerated
     */
    public Float getOriginalPrice() {
        return originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.originalPrice
     *
     * @param originalPrice the value for product.originalPrice
     *
     * @mbggenerated
     */
    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.promotePrice
     *
     * @return the value of product.promotePrice
     *
     * @mbggenerated
     */
    public Float getPromotePrice() {
        return promotePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.promotePrice
     *
     * @param promotePrice the value for product.promotePrice
     *
     * @mbggenerated
     */
    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.stock
     *
     * @return the value of product.stock
     *
     * @mbggenerated
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.stock
     *
     * @param stock the value for product.stock
     *
     * @mbggenerated
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.cid
     *
     * @return the value of product.cid
     *
     * @mbggenerated
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.cid
     *
     * @param cid the value for product.cid
     *
     * @mbggenerated
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.createDate
     *
     * @return the value of product.createDate
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.createDate
     *
     * @param createDate the value for product.createDate
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.pictName
     *
     * @return the value of product.pictName
     *
     * @mbggenerated
     */
    public String getPictName() {
        return pictName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.pictName
     *
     * @param pictName the value for product.pictName
     *
     * @mbggenerated
     */
    public void setPictName(String pictName) {
        this.pictName = pictName == null ? null : pictName.trim();
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", subTitle=" + subTitle + ", originalPrice=" + originalPrice
				+ ", promotePrice=" + promotePrice + ", stock=" + stock + ", cid=" + cid + ", createDate=" + createDate
				+ ", pictName=" + pictName + "]";
	}
    
    
}