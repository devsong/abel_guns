package com.stylefeng.guns.common.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.dto.UserSearchDto;

import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
  * 管理员表 Mapper 接口
 * </p>
 */
public interface UserMapper extends Mapper<User> {

    /**
     * 修改用户状态
     *
     * @param userId
     * @param status
     * @date 2017年2月12日 下午8:42:31
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     * @date 2017年2月12日 下午8:54:19
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<User> selectUsers(@Param("dataScope") DataScope dataScope, @Param("userSearchDto") UserSearchDto userSearchDto);

    /**
     * 设置用户的角色
     *
     * @return
     * @date 2017年2月13日 下午7:31:30
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     *
     * @param account
     * @return
     * @date 2017年2月17日 下午11:07:46
     */
    User getByAccount(@Param("account") String account);
}