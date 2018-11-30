package com.zx.yuren.data.algorithm.sort;

/**
 * 选择排序
 *
 * @author xu.qiang
 * @date 18/11/30
 */
public class Choose {

    public static void main(String[] args) {

        int[] array = new int[]{3, 10, 5, 4};
        int len = array.length;

        int minPoint;  //存储最小元素的下标
        int temp;
        for (int index = 0; index < len - 1; index++) {
            minPoint = index;
            for (int y = index + 1; y < len ; y++) {
                if (array[y] < array[minPoint]) {
                    minPoint = y;
                }
            }

            if (minPoint != index) {
                temp = array[index];
                array[index] = array[minPoint];
                array[minPoint] = temp;
            }

            System.out.print("第" + (index + 1) + "轮排序结果：");
            show(array);

        }
    }


    public static void show(int[] array) {
        for (int i : array) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

}
