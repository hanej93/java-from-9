package org.domain.lec05;

public class Main {
	// 리액티브 프로그래밍 지원을 위한 주요 API들이 추가되었다.
	public static void main(String[] args) throws InterruptedException {
		CoffeeSubscriber subscriber = new CoffeeSubscriber();
		CoffeePublisher publisher = new CoffeePublisher();

		publisher.subscribe(subscriber);

		Thread.sleep(5_000L);

		// RxJava, Reactor, Flow API
		// Flow API 추가의 의미
		// : 다양한 리액티브 라이브러리간의 호환성이 좋이지고,
		//   자바 내부에서도 리액티브 프로그래밍이 가능해졌다!

		// RxJava, Reactor, Flow API 무엇이 다른가?
	}
}
