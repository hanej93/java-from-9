package org.domain.lec11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		/**
		 * String / Files 클래스 API
		 */
		/**
		 * String
		 */
		// 1) indent
		String str = """
			   A
			BC
			DEF
			""".indent(-1);
		System.out.println(str);

		// 2) stripIndent()
		// A, B, C 앞의 공백이 한 칸씩 제거된다.
		String str2 = " A\n  B\n C";
		System.out.println(str2.stripIndent());

		// 3) formatted 함수
		// String.format()과 동일
		String str3 = """
			제 이름은 %s 이고
			나이는 %s 입니다.
			""".formatted("홍길동", 40);
		System.out.println(str3);

		/**
		 * Files 클래스
		 */
		// 1) mismatch 함수
		// : 서로 다른 파일의 내용물을 비교할 떄, 파일의 내용물이 같은지 다른지 쉽게 확인
		// : 내용이 다를 경우 첫 번째로 다른 부분의 index를, 내용이 같을 경우 -1을 반환

		/**
		 * Collectors / Stream
		 */
		// 1) teeing
		//  첫 번째 컬렉터 T 두 번쨰 컬렉터
		//   합쳐진 결과를 처리
		// Stream을 한 번만 돌면서 두가지를 작업할 때
		List<FruitDto> fruits = List.of(
			new FruitDto("사과", 100),
			new FruitDto("바나나", 200),
			new FruitDto("사과", 300),
			new FruitDto("수박", 500)
		);

		// 가장비싼, 가장싼
		Integer result = fruits.stream()
			.collect(Collectors.teeing(
				Collectors.minBy(Comparator.comparingInt(FruitDto::price)),
				Collectors.maxBy(Comparator.comparingInt(FruitDto::price)),
				(f1, f2) -> {
					f1.ifPresent(f -> System.out.printf("가장 싼 과일은 %s입니다\n", f.name()));
					f2.ifPresent(f -> System.out.printf("가장 비싼 과일은 %s입니다\n", f.name()));
					return 0;
				}
			));
		
		// 2) mapMulti 함수
		// flatMap()을 조금 더 효율적으로 사용하면서 동시에 filter, map 연산도 할 수 있는 함수
		// 선언형 스타일이 명령형 스타일로 변경
		// 불필요한 스트림 생성 x

		List<List<Number>> nums = List.of(List.of(1.0, 2.0), List.of(3, 4 ,5));
		List<Double> r2 = nums.stream()
			.flatMap(Collection::stream)
			.filter(n -> n instanceof Double)
			.map(n -> (double)n)
			.toList();

		List<Double> r1 = nums.stream()
			.<Double>mapMulti((numberList, consumer) -> {
				for (Number number : numberList) {
					if (number instanceof Double) {
						consumer.accept((double)number);
					}
				}
			})
			.toList();

		// toList

		/**
		 * 새로운 랜덤 API
		 */
		// 기존
		Random random = new Random();
		random.nextInt(10); // 0 ~ 9사이의 정수

		RandomGeneratorFactory.all()
			.forEach(f -> System.out.println("f.name() = " + f.name()));

		RandomGeneratorFactory.getDefault();

		/**
		 * 알아두면 좋은 변화
		 */
		// 1) Helpful NullPointerException

		// -> NPE 가 어디서 발생했는지 알려줌
		// User user = new User(null);
		User user = null;
		boolean isBlank = user.name.isBlank();

		// 2) 변경된 LTS 주가 -> 2년으로 변경

		// 3) 새로운 패키징 툴
		// jpackage

		// 4) 정식 기능이 된 ZGC
		// 기본은 G1GC
		
		// 5) Socker API 재구현
	}

	record User(String name) {

	}
}
