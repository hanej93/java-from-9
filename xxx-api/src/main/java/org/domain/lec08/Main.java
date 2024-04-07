package org.domain.lec08;

public class Main {
	public static void main(String[] args) {
		/**
		 * experimental
		 */
		// JVM 레벨의 기능 초기 버전 (ZGC)
		// 실험적이기 때문에 위험하거나 불완전하다.
		// 호환성도 지켜지기 매우 어렵다. (25% 정도의 완성도)
		// experimental 기능 사용을 원한다면, 전용 플래그 사용

		/**
		 * incubating
		 */
		// 모듈 형태로 배포되는 실험용 API
		// 모듈과 패키지 앞에 jdk.incubator 가 붙는다.
		// 호환성이 지켜지지 않을 수 있고, 정식 모듈 채틱시 jdk.incubator 가 사라진다.
		// incubating 기능 사용을 원한다면, JPMS 의존성 설정

		/**
		 * preview feature
		 */
		// 자바 언어 혹은 JVM과 관련된 새로운 기능
		// 완전히 구현되었지만, 피드백을 받기 위한 목적 (95% 완성도)
		// 호환되지 않을 수 있어, 프로덕션 사용은 비권장
		// --enable-preview 옵션 적용시 사용 가능
		// 정식 기능으로 포함되는 기간은 보통 두 단계 버전(1년)

		/**
		 * Text Block
		 */
		// 자바 15에서 정식
		// 여러 줄에 걸친 문자열을 만들기 위한 새로운 자바 문법
		// - 특징 !
		// 1) 시작하는 """ 다음에는 문자가 들어올 수 없다.
		// 2) \" 대신 " 만 사용해도 충분하다.('도 동일)
		// 3) 문자열 옆에 공백을 만들면 사라져버린다.
		//    -> (1) 공백을 만드는 방법 : replace로 공백을 만들어준다.
		//    -> (2) 공백을 만드는 방법 : 제일 끝의 문자 + 줄바꿈을 단순 줄바꿈으로 만든다.(fence)
		//    -> (3) 뒷 공백과 함께 사용하기: octal escape sequence 를 사용한다. \040
		//    -> (4) 새로운 이스케이프 문자(\s) 사용하기!!
		String str = """
			A"     \s
			BC'\"$$|
			DEF\040\040"""
			// .replace("$", " ");
			.replace("|\n", "\n");
		System.out.println(str);

		// 4) 한 줄로 된 긴~ 문자열을 Text Block으로 쓰고 싶은 경우
		// : 새로운 이스케이프 문자(\)를 사용하면 개행 문자가 사라진다.
		String str1 = """
			A \
			BC \
			DEF""";
		System.out.println("str1 = " + str1);
		// 5) 들여쓰기의 기준선: 문자와 닫히는 """을 기준으로
		String str2 = """
			  A
			  BC
			DEF
			""";
		System.out.println("str2 = " + str2);
	}
}
