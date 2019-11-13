package com.jdk.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>Title: LambdaTest5.java</p>
 * <p>Description:  
 * 方法引用
 * 1)静态方法引用:如果Lambda表达式的实现恰好可以通过调用一个类的静态方法来实现,那么可以使用静态方法引用
 * 	  用法: 类名::静态方法名
 * 
 * 2)实例方法引用:如果Lambda表达式的实现恰好可以通过调用一个实例的实例方法来实现,那么可以使用实例方法引用
 * 	  用法: 实例::实例方法名
 * 
 * 
 * 3)对象方法引用:
 * 4)构造方法引用:
 * </p>
 * <p>Company: http://www.jingdaka.com</p>
 * @author ZY
 * <p> Just go on !!!</p>
 * @date 2018年10月14日  下午6:33:45 
 * @version v1.0
 */
public class LambdaTest5 {

	public static void main(String[] args) {
		
		//提供一个输出(无输入有输出)
		Supplier<String> s1 = () -> "hello";
		Supplier<String> s2 = () -> LambdaTest5.getStr();
		Supplier<String> s3 = LambdaTest5::getStr; //静态方法引用
		System.out.println(s1.get());
		System.out.println(s2.get());
		System.out.println(s3.get());
		
		System.out.println("===============================");
		
		//提供一个输入(有输入无输出)
		Consumer<String> c1 = str -> {System.out.println(str);};
		Consumer<String> c2 = str -> LambdaTest5.put(str);
		Consumer<String> c3 = LambdaTest5::put; //静态方法引用
		c1.accept("hello ali1");
		c2.accept("hello ali2");
		c3.accept("hello ali3");
		
		System.out.println("===============================");
		
		//接收一个输入,提供一个输出(有输入有输出)
		Function<String, Integer> fn1 = str -> str.length();
		Function<String, Integer> fn2 = str -> LambdaTest5.getLength(str);
		Function<String, Integer> fn3 =LambdaTest5::getLength; //静态方法引用
		System.out.println(fn1.apply("zhangsan"));
		System.out.println(fn2.apply("lisi"));
		System.out.println(fn3.apply("wangwu"));
		
		System.out.println("===============================");
		
		//接收两个输入,提供一个输出(有输入有输出)
		BiFunction<String, String, Integer> bf1 = (str1,str2) -> str1.length() + str2.length();
		BiFunction<String, String, Integer> bf2 = (str1,str2) -> LambdaTest5.len(str1,str2);
		BiFunction<String, String, Integer> bf3 = LambdaTest5::len; //静态方法引用
		System.out.println(bf1.apply("zhang", "san"));
		System.out.println(bf2.apply("li", "si"));	
		System.out.println(bf3.apply("wang", "wu"));
		
		System.out.println("===============================");
		
		Supplier<String> ss1 = () -> "hello lambda";
		Supplier<String> ss2 = () -> new LambdaTest5().getInsStr();
		Supplier<String> ss3 = new LambdaTest5()::getInsStr;
		System.out.println(ss1.get());
		System.out.println(ss2.get());
		System.out.println(ss3.get());
		
		System.out.println("===============================");
		
		
	}
	
	
	
	
	/**
	 * <p>Description：实例方法</p>  
	 * @return
	 */
	public String getInsStr() {
		return "hello lambda";
	}

	/**
	 * <p>Description：静态方法(有参数有返回值)</p>  
	 * @param str1
	 * @param str2
	 * @return
	 */
	static Integer len(String str1, String str2) {
		return str1.length() + str2.length();
	}

	/**
	 * <p>Description：静态方法(有参数有返回值)</p>  
	 * @param str
	 * @return
	 */
	static Integer getLength(String str) {
		return str.length();
	}

	/**
	 * <p>Description：静态方法(无参数有返回值)</p>  
	 * @return
	 */
	static String getStr() {
		return "hello world!";
	};
	
	/**
	 * <p>Description：静态方法(有参数无返回值)</p>  
	 * @param s
	 */
	static void put(String s) {
		System.out.println(s);
	}
	
}

