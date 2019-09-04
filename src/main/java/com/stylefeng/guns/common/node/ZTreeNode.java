package com.stylefeng.guns.common.node;

import lombok.Data;

/**
 * 
 * jquery ztree 插件的节点
 * 
 */
@Data
public class ZTreeNode {
    // 节点id
    private Integer id;
    // 父节点id
    private Integer pId;
    // 节点名称
    private String name;
    // 是否打开节点
    private Boolean open;
    // 是否被选中
    private Boolean checked;

    public static ZTreeNode createParent() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setChecked(true);
        zTreeNode.setId(0);
        zTreeNode.setName("顶级");
        zTreeNode.setOpen(true);
        zTreeNode.setPId(0);
        return zTreeNode;
    }
}
