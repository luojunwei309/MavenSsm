package com.ljw.ssm.entity;

import java.io.Serializable;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.id
     *
     * @mbggenerated Fri Jul 03 13:33:32 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.name
     *
     * @mbggenerated Fri Jul 03 13:33:32 CST 2020
     */
    private String name;
    
    
    

    public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

	public Role(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.id
     *
     * @return the value of t_role.id
     *
     * @mbggenerated Fri Jul 03 13:33:32 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.id
     *
     * @param id the value for t_role.id
     *
     * @mbggenerated Fri Jul 03 13:33:32 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.name
     *
     * @return the value of t_role.name
     *
     * @mbggenerated Fri Jul 03 13:33:32 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.name
     *
     * @param name the value for t_role.name
     *
     * @mbggenerated Fri Jul 03 13:33:32 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }



	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
    
    
}