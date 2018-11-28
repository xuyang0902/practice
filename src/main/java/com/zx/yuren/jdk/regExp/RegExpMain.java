package com.zx.yuren.jdk.regExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author xu.qiang
 * @date 2018/9/10
 */
public class RegExpMain {

    public static void main(String[] args) {
        System.out.println("abc".matches("..."));
        //把数字换成 -
        System.out.println("a123456hh".replaceAll("\\d","-"));

        //正则表达式
        Pattern pattern = Pattern.compile("[a-z]{3}");
        Matcher matcher = pattern.matcher("abc");

        System.out.println(matcher.find());


        System.out.println("P".matches("[A-Z&&[PFG]]"));
        System.out.println("P".matches("[A-Z]|[a-z]"));
        System.out.println("P".matches("[^P]"));


        /**
         *
         *
         *
         字符类
         [abc] a、b 或 c（简单类）
         [^abc] 任何字符，除了 a、b 或 c（否定）
         [a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内（范围）
         [a-d[m-p]] a 到 d 或 m 到 p：[a-dm-p]（并集）
         [a-z&&[def]] d、e 或 f（交集）
         [a-z&&[^bc]] a 到 z，除了 b 和 c：[ad-z]（减去）
         [a-z&&[^m-p]] a 到 z，而非 m 到 p：[a-lq-z]（减去）

         预定义字符类
         . 任何字符（与行结束符可能匹配也可能不匹配）
         \d 数字：[0-9]
         \D 非数字： [^0-9]
         \s 空白字符：[ \t\n\x0B\f\r]
         \S 非空白字符：[^\s]
         \w 单词字符：[a-zA-Z_0-9]
         \W 非单词字符：[^\w]

         *
         */
    }

}
