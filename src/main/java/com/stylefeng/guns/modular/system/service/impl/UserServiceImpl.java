package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.common.annotion.DataSource;
import com.stylefeng.guns.common.constant.DSEnum;
import com.stylefeng.guns.common.node.MenuNode;
import com.stylefeng.guns.common.persistence.dao.MenuMapper;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.dto.UserSearchDto;
import com.stylefeng.guns.modular.system.service.IUserService;

import tk.mybatis.mapper.entity.Example;

@Component
@DataSource(DSEnum.DATA_SOURCE_GUNS)
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<User> selectUsers(DataScope dataScope, UserSearchDto userSearchDto) {
        return userMapper.selectUsers(dataScope, userSearchDto);
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user) == 1;
    }

    @Override
    public User getByAccount(String account) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("account", account);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insertSelective(user) == 1;
    }

    @Override
    public boolean updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    @Override
    public boolean setStatus(Integer userId, int status) {
        userMapper.setStatus(userId, status);
        return true;
    }

    @Override
    public boolean setRoles(Integer userId, String roleIds) {
        userMapper.setRoles(userId, roleIds);
        return true;
    }

    @Override
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleList) {
        return menuMapper.getMenusByRoleIds(roleList);
    }

}
