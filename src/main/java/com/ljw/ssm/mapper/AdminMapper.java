package com.ljw.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljw.ssm.entity.Admin;
import com.ljw.ssm.entity.AdminExample;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);
    
    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);
    
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);
    
    int updateByPrimaryKeySelective(Admin record);
    
    int updateByPrimaryKey(Admin record);
    
    /**
     * Admin分页
     * @param keyword
     * @return
     */
    List<Admin> selectAdminByKeyword(String keyword);
    /**
     * 根据adminId删除原有的角色
     * @param adminId
     */
	void deleteOldRelationship(Integer adminId);
    /**
     * 根据roleIdList和adminId保存新的关联关系
     * @param adminId
     * @param roleIdList
     */
	void insertNewRelationship(@Param("adminId")Integer adminId, @Param("roleIdList")List<Integer> roleIdList);

	/**
	 * 先删除admin的角色关系
	 */
	void deleteOldRole(Integer adminId);

	/**
	 * 在保存新的关系
	 */
	void inserNewRole(@Param("adminId")Integer adminId, @Param("roleIdList")List<Integer> roleIdList);
}