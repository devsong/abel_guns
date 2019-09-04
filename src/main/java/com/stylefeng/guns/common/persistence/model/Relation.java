package com.stylefeng.guns.common.persistence.model;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Table(name = "sys_relation")
@Data
@EqualsAndHashCode(callSuper = false)
public class Relation extends Base {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Integer menuid;
    /**
     * 角色id
     */
    private Integer roleid;
}
