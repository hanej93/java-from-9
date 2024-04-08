package org.domain.lec10;

public sealed abstract class Animal permits Dog, Cat {
}

// 한 파일에 부모와 자식 모두 있다면, permits을 생략할 수 있다.

// final class Dog extends Animal {
// 	public String bark() {
// 		return "강아지 멍멍!";
// 	}
// }
//
// final class Cat extends Animal {
// 	public String purr() {
// 		return "고양이 야옹~";
// 	}
// }
