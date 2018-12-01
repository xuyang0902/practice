package com.zx.yuren.internal.strength.algorithm.sort;

import com.zx.yuren.internal.strength.algorithm.sort.strategy.BubbleSort;

/**
 * @author xu.qiang
 * @date 18/12/1
 */
public class SortTestMain {

    public static void main(String[] args) {


        Sort sort = new BubbleSort();

        SortStrategy sortStrategy = new SortStrategy(sort);

        Integer[] list = new Integer[]{1,9,8,2,3,7,6,4,5};
        sortStrategy.sort(list);

    }

}
