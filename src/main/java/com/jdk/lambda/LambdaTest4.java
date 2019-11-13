package com.jdk.lambda;
/**
 * <p>Title: LambdaTest4.java</p>
 * <p>Description: </p>
 * <p>Company: http://www.jingdaka.com</p>
 * @author ZY
 * <p> Just go on !!!</p>
 * @date 2018年10月14日  下午6:18:49 
 * @version v1.0
 */

import java.io.Closeable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaTest4 {
	
	public static void main(String[] args) {
		
		//无参无返回值的
		Runnable r = () -> {};
		Closeable c = () -> {};
		
		//有参无返回值
		//接收一个输入
		Consumer<String> consumer = a -> {};
		
		//无参有返回值
		//提供一个输出
		Supplier<String> supplier = () -> "hello word";
		
		//有参有返回值
		//获得一个输入返回一个输出
		Function<String, Integer> function = (a) -> a.length();
	}

}

