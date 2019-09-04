package com.stylefeng.guns.modular.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.common.annotion.DataSource;
import com.stylefeng.guns.common.constant.DSEnum;
import com.stylefeng.guns.common.persistence.dao.RelationMapper;
import com.stylefeng.guns.common.persistence.dao.RoleMapper;
import com.stylefeng.guns.common.persistence.model.Relation;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.modular.system.service.IRoleService;

@Service
@DataSource(DSEnum.DATA_SOURCE_GUNS)
@Transactional()
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public void setAuthority(Integer roleId, String ids) {
        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);
        // 添加新的权限
        for (Integer id : Convert.toIntArray(ids)) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
    }

    @Override
    public void delRoleById(Integer roleId) {
        // 删除角色
        this.roleMapper.deleteByPrimaryKey(roleId);
        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);
    }
}
