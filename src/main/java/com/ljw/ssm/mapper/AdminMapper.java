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
     * Admin��ҳ
     * @param keyword
     * @return
     */
    List<Admin> selectAdminByKeyword(String keyword);
    /**
     * ����adminIdɾ��ԭ�еĽ�ɫ
     * @param adminId
     */
	void deleteOldRelationship(Integer adminId);
    /**
     * ����roleIdList��adminId�����µĹ�����ϵ
     * @param adminId
     * @param roleIdList
     */
	void insertNewRelationship(@Param("adminId")Integer adminId, @Param("roleIdList")List<Integer> roleIdList);

	/**
	 * ��ɾ��admin�Ľ�ɫ��ϵ
	 */
	void deleteOldRole(Integer adminId);

	/**
	 * �ڱ����µĹ�ϵ
	 */
	void inserNewRole(@Param("adminId")Integer adminId, @Param("roleIdList")List<Integer> roleIdList);
}