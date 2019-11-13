package com.jdk.lambda;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.jdk.lambda.interfaces.UserMapper;
import com.jdk.lambda.interfaces.UserMapper1;

/**
 * <p>Title: LambdaTest3.java</p>
 * <p>Description: Lambda表达式的书写方式</p>
 * <p>Company: http://www.jingdaka.com</p>
 *
 * @author ZY
 * <p> Just go on !!!</p>
 * @version v1.0
 * @date 2018年10月14日  下午5:28:02
 */
public class LambdaTest3 {

    public static void main(String[] args) throws Exception {

        // 1.无参无返回值

        // 1.8之前的方式
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        r1.run();

        // 1.8之后的方式
        Runnable r2 = () -> System.out.println("run");
        r2.run();

        Runnable r3 = () -> {
            System.out.println("run1");
            System.out.println("run2");
        };
        r3.run();


        // 2.无参有返回值

        // 1.8之前的方式
        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        };
        System.out.println(c1.call());

        // 1.8之后的方式
        Callable<String> c2 = () -> {
            return "hello world";
        };
        System.out.println(c2.call());

        Callable<String> c3 = () -> "hello world";
        System.out.println(c3.call());

        // 3.无参有返回值

        // 1.8之前的方式
        UserMapper1 u1 = new UserMapper1() {
            @Override
            public int update() {
                return 0;
            }
        };
        System.out.println(u1.update());

        // 1.8之后的方式
        UserMapper1 u2 = () -> {
            return 1;
        };
        System.out.println(u2.update());

        UserMapper1 u3 = () -> 1;
        System.out.println(u3.update());

        // 4.有参有返回值

        // 1.8之前的方式
        UserMapper um1 = new UserMapper() {
            @Override
            public int plus(int a, int b) {
                return a + b;
            }
        };
        System.out.println(um1.plus(5, 8));

        //1.8之后的方式
        UserMapper um2 = (a, b) -> {
            return a + b;
        };
        System.out.println(um2.plus(6, 9));

        UserMapper um3 = (a, b) -> a + b;
        System.out.println(um3.plus(6, 9));

        //输入任意整数,返回字符串
        Function<Integer, String> function1 = a -> {
            return "hahaha";
        };
        System.out.println(function1.apply(123));

        //输入任意整数,返回从0加到该整数的和
        Function<Integer, Integer> function2 = (a) -> {
            Integer sum = 0;
            for (int i = 0; i <= a; i++) {
                sum += i;
            }
            return sum;
        };
        System.out.println(function2.apply(10));

        //两个输入一个输出
        BiFunction<String, String, Integer> bf = (a, b) -> a.length() + b.length();
        System.out.println(bf.apply("zhang", "san"));

    }
}

