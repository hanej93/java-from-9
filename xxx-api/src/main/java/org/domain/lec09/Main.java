package org.domain.lec09;

public class Main {
	public static void main(String[] args) {
		/**
		 * switch expression
		 */
		// statement vs expression
		// statement : 프로그래밍 코드 문장(구문)
		// expression : 프로그래밍 문장이긴 한데, 어떠한 결과값이 정해지는 문장
		// - 특징
		// 1) 반드시 최종 결과가 하나의 값으로 만들어져야 한다.
		// 2) 예외를 던지는 것은 가능하다.
		// 3) Enum 과 함께 사용하면, 시너지 효과를 받을 수 있다.

		/**
		 * instanceof pattern matching
		 */
		// instanceof : 어떤 변수가 특정 타입의 인스턴스인지 확인
		// - 특징
		// 1) 변수의 타입을 확인하고, 원하는 타입이 맞다면 값을 바로 할당해주는 기능
	}

	enum Color {RED, YELLOW, GREEN}

	public String getSignal(Color color) {
		return switch (color) {
			case RED -> "멈추세요.";
			case YELLOW -> "곧 빨간색으로 바뀝니다.";
			case GREEN -> "지나가세요.";
		};
	}

	private String calculateTestGrade(int score) {
		String grade = "";
		switch (score) {
			case 5:
				grade = "A";
				break;
			case 4:
			case 3:
				grade = "B";
				break;
			case 2:
			case 1:
				grade = "C";
				break;
			default:
				grade = "F";
		}
		return grade;
	}

	private String calculateTestGrade2(int score) {
		return switch (score) {
			case 5:
				yield "A";
			case 4, 3:
				yield "B";
			case 2, 1:
				yield "C";
			default:
				yield "F";
		};
	}

	private String calculateTestGrade3(int score) {
		return switch (score) {
			case 5 -> "A"; // yield 생략
			case 4, 3 -> {
				System.out.println("score = " + score);
				yield "B";
			}
			case 2, 1 -> throw new IllegalArgumentException();
			default -> "F";
		};
	}
}
