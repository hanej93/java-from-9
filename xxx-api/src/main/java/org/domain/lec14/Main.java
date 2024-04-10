package org.domain.lec14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SequencedMap;

public class Main {
	public static void main(String[] args) {
		/**
		 * Math.clamp()
		 */
		int result = Math.clamp(220, 5, 15);
		System.out.println(result);

		/**
		 * String
		 */
		// 1) String indexOf

		// 2) String splitWithDelimiters

		String str = "A;B;C";

		// [ [A, ;, B, ;, C]
		String[] results = str.splitWithDelimiters(";", - 1);
		Arrays.stream(results).forEach(d -> System.out.println(d));

		// 3) StringBuffer/Builder repeat 함수

		// 4) Character 클래스 - emoji 관련 함수들 추가

		// 5) Sequenced Collection API
		// : 순서가 있는 컬렉션의 공통된 기능
		List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4));
		numbers.get(numbers.size() - 1);
		numbers.getLast();
		List<Integer> reversed = numbers.reversed(); // 원본을 가리키는 뷰이다!

		numbers.add(6);
		System.out.println("reversed = " + reversed);

		SequencedMap<Integer, String> map = new LinkedHashMap<>();
		map.put(1, "A");
		map.put(2, "B");

		var entry = map.firstEntry();
		// entry.setValue("D");
	}
}
