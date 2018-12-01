package com.zx.yuren.internal.strength.algorithm.sort.util;

/**
 * @author Zhang Peng
 */
public class ArrayUtil {

    public static <T> void debugLogArray(T[] list, int begin, int end, String tip) {
        String content = tip + getArrayString(list, begin, end);
        System.out.println(content);
    }

    public static <T> String getArrayString(T[] list, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < begin; i++) {
            sb.append("\t");
        }
        int count = 0;
        for (int i = begin; i <= end; i++) {
            sb.append(list[i] + "\t");
            if (++count == 10) {
                sb.append("\n");
                count = 0;
            }
        }

        return sb.toString();
    }

}
