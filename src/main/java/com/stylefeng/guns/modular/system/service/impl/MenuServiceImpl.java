package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.common.node.MenuNode;
import com.stylefeng.guns.common.node.ZTreeNode;
import com.stylefeng.guns.common.persistence.dao.MenuMapper;
import com.stylefeng.guns.common.persistence.model.Menu;
import com.stylefeng.guns.modular.system.service.IMenuService;

import tk.mybatis.mapper.entity.Example;

/**
 * 菜单服务
 */
@Component
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void delMenu(Integer menuId) {
        // 删除菜单
        this.menuMapper.deleteByPrimaryKey(menuId);
        // 删除关联的relation
        this.menuMapper.deleteRelationByMenu(menuId);
    }

    @Override
    public void delMenuContainSubMenus(Integer menuId) {
        Menu menu = menuMapper.selectByPrimaryKey(menuId);

        // 删除当前菜单
        delMenu(menuId);

        // 删除所有子菜单
        Example example = new Example(Menu.class);
        example.createCriteria().andLike("pcodes", "%[" + menu.getCode() + "]%");
        List<Menu> menus = menuMapper.selectByExample(example);
        for (Menu temp : menus) {
            delMenu(temp.getId());
        }
    }

    @Override
    public List<Map<String, Object>> selectMenus(String condition, String level) {
        return null;
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {

        return null;
    }

    @Override
    public List<ZTreeNode> menuTreeList() {

        return null;
    }

    @Override
    public List<ZTreeNode> menuTreeListByMenuIds(List<Integer> menuIds) {

        return null;
    }

    @Override
    public int deleteRelationByMenu(Integer menuId) {

        return 0;
    }

    @Override
    public List<String> getResUrlsByRoleId(Integer roleId) {

        return null;
    }

    @Override
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds) {

        return null;
    }
}
