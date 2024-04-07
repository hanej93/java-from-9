package org.domain.lec04;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 1. Collection 기능 추가
		// 정적 팩토리 of() 는 불편이다.
		List<Integer> nums = List.of(1, 2);
		// nums.add(3);
		// nums.remove(1);

		Set<Integer> sets = Set.of(1, 2);
		
		// 2. Optional, Stream 기능 추가

		// 1) Optional - 3가지 기능 추가
		// 	- ifPresentOrElse
		//  - or -> 체이닝을 통해 값이 없을 경우 다른 Optional을 반환
		//  - stream

		// 2) stream 4가지
		//  - takeWhile : 하나라도 실패하면 실패한 순간부터 모두 버림
		//  - dropWile  : 조건이 true 인 경우 계속 버리다가 false 를 만나면 모두 남긴다.
		//  - ofNullable : null 이면 비어있는 스트림, 값이 있으면 원소가 하나인 Stream
		//  - 개선된 iterate
		Stream.iterate(0, i -> i < 10, i -> i + 2)
			.forEach(System.out::println);

		// 3. CompletableFuture API 기능 추가
		//  1) CompletableFutre 복사 기능
		//  2) default Executor를 가져오는 기능
		//  3) 타임아웃/지연 실행 기능
		// 5초간 기다렸다 '작업 완료'를 출력하는 Runnable
		Runnable sleep = () -> {
			try {
				Thread.sleep(500L);
				System.out.println(System.currentTimeMillis() + " - 작업 완료");
			} catch (InterruptedException e) {

			}
		};
		System.out.println(System.currentTimeMillis() + " - 작업 실행");
		CompletableFuture<Void> future = CompletableFuture.runAsync(sleep)
			// .orTimeout(1, TimeUnit.SECONDS);	// 정해진 시간안에 작업이 완료되지 않으면, 예외 발생!
			.completeOnTimeout(null, 1, TimeUnit.SECONDS); // 정해진 시간안에 작업이 완료되지 않으면, 정해진 값 반환!

		// 우리가 실행시킨 비동기 시간이 완료될 때까지 현재 스레드를 멈추는 기능!
		future.get();

		//  4) 지연 기능 - 새로운 함수가 있지는 않고, DelayedExecutor를 사용하면 된다.
		Executor executor = CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS);
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(sleep, executor);

		System.out.println(System.currentTimeMillis() + " - 작업 실행2");
		future2.get();
		
		// 4. Process API 추가
		// : ProcessHandle : native 프로세스를 제어할 수 있는 인터페이스
		System.out.println(ProcessHandle.current().pid());
		// Process : 프로세스 자체를 표현
		// ProcessHandle : 프로세스를 제어하는 기능들
		// ProcessHandle.info : 프로세스 관련 다양한 정보 조회

		// 5. 그 외 추가적인 변화
		//  1) Stack-Walking API 추가 : 스택을 훑는 기능
		//    : 지금 시정의 스택 프레임을 제어하는 기능
		callA();
		//  2) 내부 문자열 처리 방식 개선 (compact String)
		//  3) JDK 버전 스키마 방식 변경 (메이저.마이너.보안패치)
		//  4) 메모리 및 GC 관련 변경 사항
		//    : default GC : G1GC (논리적 구분)
		//    (기존 Parallel GC - 물리적 구분)
		//    : 기존 GC 튜닝에 사용되던 CMS GC는 deprecated 되었다.
		//   -  OutOfMemoryError 관련 옵션 추가
	}

	private static void callA() {
		callB();
	}

	private static void callB() {
		callC();
	}

	private static void callC() {
		List<String> walk = StackWalker.getInstance()
			.walk(s -> s.map(StackWalker.StackFrame::getMethodName))
			.collect(Collectors.toList());

		for (String s : walk) {
			System.out.println("Stack = " + s);
		}
	}
}
