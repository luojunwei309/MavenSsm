package com.ljw.ssm.entity;

public class Category {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.pictname
     *
     * @mbggenerated
     */
    private String pictname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.id
     *
     * @return the value of category.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.id
     *
     * @param id the value for category.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.name
     *
     * @return the value of category.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.name
     *
     * @param name the value for category.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.pictname
     *
     * @return the value of category.pictname
     *
     * @mbggenerated
     */
    public String getPictname() {
        return pictname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.pictname
     *
     * @param pictname the value for category.pictname
     *
     * @mbggenerated
     */
    public void setPictname(String pictname) {
        this.pictname = pictname == null ? null : pictname.trim();
    }
}