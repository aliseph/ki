package com.ingta.framework.util;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-4-15 上午08:49:46 
 * 说明：元组辅助类，用于多种类型值的返回，如在分页的时候，后台存储过程既返回了查询得到的
 * 当页的数据（List类型），又得到了数据表中总共的数据总数（Integer类型），然后将这
 * 两个参数封装到该类中返回到action中使用
 * 使用泛型方法实现，利用参数类型推断，编译器可以找出具体的类型
 */
public class TupleUtil {
	
	public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}

	public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}
}
