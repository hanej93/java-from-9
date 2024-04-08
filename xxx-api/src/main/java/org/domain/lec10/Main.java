package org.domain.lec10;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		/**
		 *  Record Class
 		 */
		// : 데이터 전달을 위해 등장한 클래스
		// 1. class 에 final이 선언되어 있어서 상속받지 못한다.
		// 2. private final 필드만 선언되어 있다.
		// 3. 모든 private final 필드에 대해 생성자가 존재한다.
		// 4. 클래스가 갖고 있는 모든 필드에 접근할 수 있는 메소드가 있고, 메소드의 이름은 필드의 이름과 동일하다.
		// 5. equals() / hashCode() / toString() 이 존재한다.

		// Record Class
		// 1. 다른 클래스가 record class를 상속 받을 수 없다.
		// 2. 컴포넌트에 대한 private final 필드가 자동 생성
		// 3. name, price, date 를 받아 필드에 값을 할당하는 생성자 자동 생성
		// 4. 세 필드에 접근할 수 있는 name() / price() / date() 접근자 자동 생성
		// 5. equals() / hashCode() / toString() 자동 생성

		// 특징
		// 1. 다른 클래스를 상속 받을 수 없다.
		// 2. 인터페이스는 구현가능하다.
		// 3. static 필드, 함수, 인스턴스 함수 등을 만들 수 있다.
		// 4. 자동 생성되는 메소드들을 직접 재정의 할 수 있다.

		// compact constructor
		// : 매개변수를 전혀 받지 않아 문법적으로 간결하다.
		// : 대신, 필드에 값을 할당할 수는 없다.

		// Record Class 와 어노테이션
		// 1. 특정 언어 요소에 어노테이션을 강제하고 싶다면,
		// @Target 메타 어노테이션을 이용할 수 있다.

		FruitDtoV2 dto = new FruitDtoV2("사과", -1000, LocalDate.of(2024, 4, 8));
		System.out.println("dto.price() = " + dto.price());
		System.out.println("dto.name() = " + dto.name());
		System.out.println("dto = " + dto);
		
		// Record Class 와 스프링 부트
		// Jackson 2.12.0 이상에서 record class는 사용 가능 하다.(Spring Boot 2.5.x 이상)
		// HTTP 쿼리 파라미터와 바디 모두 잘 적용된다!

		/**
		 * Sealed Class
		 */
		// Animal 의 하위 클래스로 Dog / Cat, 둘 만 두고 싶다!
		// 하위 클래스를 지정된 클래스로만 제한한다! (봉인한다!)

		// Sealed Class 의 장점?
		// 1. 상위 클래스를 설계할 때 호환성 걱정을 덜 수 있다.
		// 2. enum class 처럼 sealed class를 사용할 수 있다.

		// Sealed Interface
		// sealed class와 동일한 interface

		// Sealed 는 언제 사용해야 할까?
		// 추상 클래스나 인터페이스를 만드려 하는데,
		// 하위 클래스 경우의 수를 제한하고 싶을 때
	}
}
