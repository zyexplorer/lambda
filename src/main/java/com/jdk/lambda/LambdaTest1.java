package com.jdk.lambda;

/**
 * <p>Title: LambdaTest.java</p>
 * <p>Description: 执行多线程任务</p>
 * <p>Company: http://www.jingdaka.com</p>
 *
 * @author ZY
 * <p> Just go on !!!</p>
 * @version v1.0
 * @date 2018年10月14日  下午4:32:13
 */
public class LambdaTest1 {

    public static void main(String[] args) {

        // 不使用lambda表达式 需要4行代码
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行输出的线程");
            }
        }).start();

        // 使用lambda表达式 1行代码搞定
        new Thread(() -> System.out.println("执行输出的线程")).start();

        // 使用lambda表达式
        new Thread(() -> {
            // 执行输出线程
            shuchu1("输出1");
            output2("输出2");
        }).start();

    }

    public static void shuchu1(String a) {
        System.out.println(a);
    }

    public static void output2(String a) {
        System.out.println(a);
    }
}

