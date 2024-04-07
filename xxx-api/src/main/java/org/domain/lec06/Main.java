package org.domain.lec06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.w3c.dom.ls.LSOutput;

public class Main {
	public static void main(String[] args) {
		/**
		 * 1. 지역 변수의 타입을 추론하는 새로운 예약어 (var)
		 */
		var num = 3;
		num = 10;
		// num = "ABC";

		var list = List.of(1, 2, 3);

		Map<String, String> map = Map.of("A", "B");
		var map2 = Map.of("A", "B");

		var var = 3;
		
		// var 사용에 주의해야 하는 경우
		// 1) 타입을 추론하는 예약어 이기 때문에, 값이 없거나 null이면 안된다.
		// var num;
		// var a = null;

		// 2) 람다식 혹은 배열 타입은 추론할 수 없다.
		// var b = (String s) -> System.out.println(s);
		// var c = {"A", "B", "C"};

		// 3) 다이아몬드 연산자와 var을 함께 쓰면, <Object>로 간주한다.
		var nums = new ArrayList<>();

		// 4) 익명 클래스와 함께 쓸 수 있지만, 별도의 타입으로 간주한다.
		Object obj1 = new Object() {};
		obj1 = new Object();

		var obj2 = new Object() {};
		// obj2 = new Object();

		/**
		 * 2. 컬렉션 추가 API
		 */
		// copyOf() 메소드 List / Set / Map 의 원본 컬렉션을 깊게 복사한다.
		List<Integer> oldNums = new ArrayList<>();
		oldNums.add(1);
		oldNums.add(2);

		List<Integer> newNums = List.copyOf(oldNums);
		oldNums.add(3);

		List<Integer> newNums2 = Collections.unmodifiableList(oldNums);

		oldNums.forEach(x -> System.out.println("old = " + x));	// 1, 2, 3
		newNums.forEach(x -> System.out.println("new = " + x));	// 1, 2
		newNums2.forEach(x -> System.out.println("new2 = " + x));	// 1, 2, 3
		
		// Collectors.toUnmodifiableList() 추가
		List<Integer> finalList = Stream.of(1, 2, 3, 4, 5)
			.filter(x -> x % 2 == 0)
			.collect(Collectors.toUnmodifiableList());

		/**
		 * 3. Optional 추가 API
		 */
		// orElsThrow()
		// -> 매개변수 없이 사용하면, NoSuchElementException 이 발생
		Optional.ofNullable(3)
			.orElseThrow();

		/**
		 * 4. 시간 기반의 배포 버전 관리
		 */
		// 자바 10에서는 x.y.z.u  같은 형태로 변경
		// - y는 0으로 고정
		// ex) 10.0.1.2
		// 메이저 버전은 6개월마다 출시된다.
		// - z는 첫 출시 한 달 이후 1이 올라가고, 그 후 3개월마다 1이 추가로 올라간다.
		// - 긴급한 패치가 필요하면 u가 올라간다.
		// 3년에 한 번은 LTS 버전이 출시된다.

		/**
		 * G1GC의 성능 개선
		 */
		// 자바 9에서 default GC가 되었던 G1GC의 병렬 처리가 개선
	}
}
