package com.ingta.framework.util;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-4-15 上午08:47:57 说明：拥有三个元素的元组 用于在一个方法里返回三种类型的值
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	private static final long serialVersionUID = 4444087190877204619L;
	public final C third;

	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		this.third = c;
	}
}
