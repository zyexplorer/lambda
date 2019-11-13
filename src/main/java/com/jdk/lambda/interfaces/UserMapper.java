package com.jdk.lambda.interfaces;
/**
 * <p>Title: UserMapper.java</p>
 * <p>Description: 只有一个抽象方法(Object类中的方法及其他方法均不算)的接口为函数式接口
 * 		有参有返回值的函数式接口
 * </p>
 * <p>Company: http://www.jingdaka.com</p>
 * @author ZY
 * <p> Just go on !!!</p>
 * @date 2018年10月14日  下午5:00:54 
 * @version v1.0
 */
@FunctionalInterface
public interface UserMapper {
	
	int plus(int a,int b);
	
	int hashCode();
	
	default int insert() {
		return 1;
	}
	
	static int update() {
		return 1;
	}
}

