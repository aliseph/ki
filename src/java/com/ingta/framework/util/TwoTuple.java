package com.ingta.framework.util;

import java.io.Serializable;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-4-15 上午08:46:18 说明：拥有二个元素的元组 用于在一个方法里返回两种类型的值
 */
public class TwoTuple<A, B> implements Serializable {
	private static final long serialVersionUID = -3490720001215746966L;
	public final A first;
	public final B second;

	public TwoTuple(A a, B b) {
		this.first = a;
		this.second = b;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(first).append("|").append(second);
		return sb.toString();
	}
}
