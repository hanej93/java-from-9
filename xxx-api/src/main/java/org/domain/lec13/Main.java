package org.domain.lec13;

import java.util.FormatProcessor;

public class Main {

	public static void main(String[] args) {
		/**
		 * 1. unnamed class
		 */
		// : main 메소드가 간단해지는 기능
		// : 애플리케이션의 시작점으로만 가능(다른 클래스에서 호출 불가)

		/**
		 * 2. unnamed patterns, unnamed variable
		 */
		// 사용하지 않은 패턴 매칭, 변수를 _로 바꿀 수 있다.

		/**
		 * 3. String template
		 */
		String name = "홍길동";
		int age = 50;
		String str = STR."이름: \{name} 나이: \{age}";
		System.out.println(str);

		StringTemplate template = StringTemplate.RAW."이름: \{name} 나이: \{age}";
		System.out.println(template.fragments());
		System.out.println(template.values());
		System.out.println(template.interpolate());

		double num = 1.333;
		String str2 = FormatProcessor.FMT."숫자 : %.2f\{num}";
		System.out.println(str2);
	}


	public static void findDistance(Object object) {
		// y1, y2를 쓰지 않고 x 좌표의 차이만 계산해보자!
		if (object instanceof Line(Point(var x1, var _), Point(var x2, _))) {
		}
	}

	record Point(double x, double y) {
	}

	record Line(Point p1, Point p2) {

	}
}
