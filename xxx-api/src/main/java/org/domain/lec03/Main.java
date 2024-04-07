package org.domain.lec03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	@SafeVarargs
	public static <T> List<T> flatten(List<T>... lists) {
		// Object[] obj = lists; // List<T>[]
		// obj[0] = 1;

		List<T> result = new ArrayList<>();
		for (List<T> list : lists) {
			result.addAll(list);
		}
		return result;
	}

	public static void main(String[] args) {
		// 1. try-with resources
		
		// 2. @saveVarargs를 private 메소드에도 적용
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5);
		List<Integer> result = flatten(list1, list2);
		System.out.println("result = " + result);

		// 3. 익명 inner class + diamond Syntax
		InnerClass<Object> ic = new InnerClass<>(3) {};

		// 4. 인터페이스 + private method
		
		// 5. undersocres naming이 불가능
		// int _ = 3;
	}

	public static class InnerClass<T> {
		private final T t;

		public InnerClass(T t) {
			this.t = t;
		}
	}
}
