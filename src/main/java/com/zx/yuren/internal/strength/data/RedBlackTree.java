package com.zx.yuren.internal.strength.data;

import java.io.Serializable;

/**
 * 红黑树
 *
 * @author xu.qiang
 * @date 18/11/5
 */
public class RedBlackTree<T extends Comparable> implements Serializable {

    private static final long serialVersionUID = -6704502700536519766L;


    /**
     * 根节点
     */
    private Node<T> root;

    /**
     * 高度
     */
    private int height;

    /**
     * 根节点到每个叶子节点的黑色节点数
     */
    private int blackCount;


    public RedBlackTree() {

    }

    private void add(T t){

    }


    /**
     * 树节点
     */
    static class Node<T> {

        public static final int RED = 0;
        public static final int BLACK = 1;

        public static final int color = RED;

        //当前节点值
        private T value;

        //左孩子
        private Node<T> left;

        //右孩子
        private Node<T> right;

        //父节点
        private Node<T> parent;


        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }


}
