package com.stylefeng.guns.common.constant.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.common.constant.enums.ManagerStatus;
import com.stylefeng.guns.common.constant.enums.MenuStatus;
import com.stylefeng.guns.common.persistence.dao.DeptMapper;
import com.stylefeng.guns.common.persistence.dao.DictMapper;
import com.stylefeng.guns.common.persistence.dao.MenuMapper;
import com.stylefeng.guns.common.persistence.dao.NoticeMapper;
import com.stylefeng.guns.common.persistence.dao.RoleMapper;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.Dept;
import com.stylefeng.guns.common.persistence.model.Dict;
import com.stylefeng.guns.common.persistence.model.Menu;
import com.stylefeng.guns.common.persistence.model.Notice;
import com.stylefeng.guns.common.persistence.model.Role;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.core.util.ToolUtil;

import tk.mybatis.mapper.entity.Example;

@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {
    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
    private NoticeMapper noticeMapper = SpringContextHolder.getBean(NoticeMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public String getUserNameById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            return user.getName();
        }
        return "--";
    }

    @Override
    public String getUserAccountById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            return user.getAccount();
        }
        return "--";
    }

    @Override
    public String getRoleName(String roleIds) {
        Integer[] roles = Convert.toIntArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (int role : roles) {
            Role roleObj = roleMapper.selectByPrimaryKey(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    @Override
    public String getSingleRoleName(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectByPrimaryKey(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    public String getSingleRoleTip(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectByPrimaryKey(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }

    @Override
    public String getDeptName(Integer deptId) {
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
            return dept.getFullname();
        }
        return "";
    }

    @Override
    public String getMenuNames(String menuIds) {
        Integer[] menus = Convert.toIntArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (int menu : menus) {
            Menu menuObj = menuMapper.selectByPrimaryKey(menu);
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    @Override
    public String getMenuName(Integer menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        }
        Menu menu = menuMapper.selectByPrimaryKey(menuId);
        if (menu == null) {
            return "";
        }
        return menu.getName();
    }

    @Override
    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        }
        Menu param = new Menu();
        param.setCode(code);
        Menu menu = menuMapper.selectOne(param);
        if (menu == null) {
            return "";
        }
        return menu.getName();
    }

    @Override
    public String getDictName(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        Dict dict = dictMapper.selectByPrimaryKey(dictId);
        if (dict == null) {
            return "";
        }
        return dict.getName();
    }

    @Override
    public String getNoticeTitle(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        Notice notice = noticeMapper.selectByPrimaryKey(dictId);
        if (notice == null) {
            return "";
        }
        return notice.getTitle();
    }

    @Override
    public String getDictsByName(String name, Integer val) {
        Dict temp = new Dict();
        temp.setName(name);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return "";
        }
        Example example = new Example(Dict.class);
        example.createCriteria().andEqualTo("pid", dict.getId());
        List<Dict> dicts = dictMapper.selectByExample(example);
        for (Dict item : dicts) {
            if (item.getNum() != null && item.getNum().equals(val)) {
                return item.getName();
            }
        }
        return "";
    }

    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", sex);
    }

    @Override
    public String getStatusName(Integer status) {
        return ManagerStatus.valueOf(status);
    }

    @Override
    public String getMenuStatusName(Integer status) {
        return MenuStatus.valueOf(status);
    }

    @Override
    public List<Dict> findSubDict(Integer id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        }
        Example example = new Example(Dict.class);
        example.createCriteria().andEqualTo("pid", id);
        return dictMapper.selectByExample(example);
    }

    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    @Override
    public List<Integer> getSubDeptId(Integer deptid) {
        Example example = new Example(Dept.class);
        example.createCriteria().andLike("pids", "%[" + deptid + "]%");
        List<Dept> depts = this.deptMapper.selectByExample(example);

        ArrayList<Integer> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getId());
            }
        }

        return deptids;
    }

    /**
     * 获取所有父部门id
     */
    @Override
    public List<Integer> getParentDeptIds(Integer deptid) {
        Dept dept = deptMapper.selectByPrimaryKey(deptid);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }
}
