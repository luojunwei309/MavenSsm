package com.ljw.ssm.entity;

public class Property {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column property.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column property.cid
     *
     * @mbggenerated
     */
    private Integer cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column property.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column property.id
     *
     * @return the value of property.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column property.id
     *
     * @param id the value for property.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column property.cid
     *
     * @return the value of property.cid
     *
     * @mbggenerated
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column property.cid
     *
     * @param cid the value for property.cid
     *
     * @mbggenerated
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column property.name
     *
     * @return the value of property.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column property.name
     *
     * @param name the value for property.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "Property [id=" + id + ", cid=" + cid + ", name=" + name + "]";
	}
    
}