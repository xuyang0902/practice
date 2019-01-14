package com.zx.yuren.internal.strength.algorithm.sort;

import com.zx.yuren.internal.strength.algorithm.sort.strategy.BubbleSort;
import com.zx.yuren.internal.strength.algorithm.sort.strategy.QuickSort;

/**
 * @author xu.qiang
 * @date 18/12/1
 */
public class SortTestMain {

    public static void main(String[] args) {


        Sort sort = new QuickSort();

        SortStrategy sortStrategy = new SortStrategy(sort);

        Integer[] list = new Integer[]{9,7,5,6};
        sortStrategy.sort(list);

    }

}
