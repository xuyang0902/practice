package com.zx.yuren.data.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author xu.qiang
 * @date 18/11/30
 */
public class Bubbling {

    public static void main(String[] args) {

        int[] array = new int[]{3, 10, 5, 4};

        int len = array.length;

        int temp;
        for (int x = 0; x < len - 1; x++) {  //外层循环：每循环一次就确定了一个相对最大元素
            boolean sorted = false;//每次进来都需要排序
            for (int y = 1; y < len - x; y++) {  //内层循环：有i个元素已经排好，根据i确定本次的比较次数
                if (array[y - 1] > array[y]) {  //如果前一位大于后一位，交换位置
                    temp = array[y - 1];
                    array[y - 1] = array[y];
                    array[y] = temp;

                    sorted = true;//内层循环，确实需要排序。
                }
            }

            System.out.print("第" + (x + 1) + "轮排序结果：");
            show(array);

            if (!sorted) {
                break;
            }
        }
    }


    public static void show(int[] array) {
        for (int i : array) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
