package com.ljw.ssm.entity;

import java.util.List;

public class PropertyValue {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.pid
     *
     * @mbggenerated
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.ptid
     *
     * @mbggenerated
     */
    private Integer ptid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.value
     *
     * @mbggenerated
     */
    private String value;
    
    private Property name;
    




	public Property getName() {
		return name;
	}

	public void setName(Property name) {
		this.name = name;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.id
     *
     * @return the value of propertyvalue.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.id
     *
     * @param id the value for propertyvalue.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.pid
     *
     * @return the value of propertyvalue.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.pid
     *
     * @param pid the value for propertyvalue.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.ptid
     *
     * @return the value of propertyvalue.ptid
     *
     * @mbggenerated
     */
    public Integer getPtid() {
        return ptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.ptid
     *
     * @param ptid the value for propertyvalue.ptid
     *
     * @mbggenerated
     */
    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.value
     *
     * @return the value of propertyvalue.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.value
     *
     * @param value the value for propertyvalue.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }


    
    
}