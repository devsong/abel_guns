package com.stylefeng.guns.modular.system.service;

import java.util.List;

import com.stylefeng.guns.common.node.MenuNode;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.dto.UserSearchDto;

public interface IUserService {
    List<User> selectUsers(DataScope dataScope, UserSearchDto userSearchDto);

    User selectByPrimaryKey(Integer userId);

    boolean updateByPrimaryKey(User user);

    User getByAccount(String account);

    boolean insert(User createUser);

    boolean updateByPrimaryKeySelective(User createUser);

    boolean setStatus(Integer userId, int code);

    boolean setRoles(Integer userId, String roleIds);

    List<MenuNode> getMenusByRoleIds(List<Integer> roleList);
}
