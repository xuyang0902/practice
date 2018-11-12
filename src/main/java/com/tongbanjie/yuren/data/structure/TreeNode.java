package com.tongbanjie.yuren.data.structure;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 树节点
 *
 * @author xu.qiang
 * @date 18/11/5
 */
public class TreeNode<T extends Comparator> implements Serializable {

    private static final long serialVersionUID = -6704502700536519766L;


    //当前节点值
    private T value;

    //左孩子
    private TreeNode<T> left;

    //右孩子
    private TreeNode<T> right;

    //父节点
    private TreeNode<T> parent = null;


}
