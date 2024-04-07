package org.domain.lec07;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		/**
		 * 1. 언어적 변경 내용
		 */
		/**
		 * 자바 10에서 등장한 var을 람다식 매개변수에 적용 가능
		 */
		// String 타입을 명시적으로 작성해 준 람다식
		Consumer<String> c1 = (String x) -> System.out.println(x);
		// 자바 11 부터 람다식에 var 을 사용할 수 있다.
		Consumer<String> c2 = (var x) -> System.out.println(x);

		/**
		 * 2. 주요 API 변경 내용
		 */
		/**
		 * String 클래스 업데이트
		 */
		// 1) strip() 함수
		// : 띄어쓰기 혹은 탭 같은 white space를 앞 뒤로 제거하는 함수
		String str = "   A BC  ";
		System.out.println(str.strip());
		System.out.println(str.stripLeading());
		System.out.println(str.stripTrailing());
		
		// 2) isBlank() 함수
		// : 특정 문자열이 white space 로만 구성되어 있다면 true
		String str1 = "A";
		System.out.println("str1.isBlank() = " + str1.isBlank());

		String str2 = "   ";
		System.out.println("str2.isBlank() = " + str2.isBlank());
		
		// 3) lines() 함수
		// : 개행 문자를 기준으로 문자열 쪼개 Stream<String> 을 반환
		String str3 = "A\rB\r\nC\nD";
		str3.lines().forEach(System.out::println);

		// 4) repeat() 함수
		// : 반복 횟수를 파라미터로 받아, 주어진 문자열을 반복해 이어붙인 문자열을 반환한다.
		String str4 = "A ";
		System.out.println("str4.repeat(3) = " + str4.repeat(3));

		/**
		 * Collection API 업데이트
		 */
		// 1) toArray() : 컬렉션을 배열로 쉽게 만들 수 있는 메서드
		List<String> strings = List.of("A", "B", "C");

		// 기존에는 배열을 직접 만들어 옮겨야 했다.
		String[] strArray = new String[3];
		String[] result = strings.toArray(strArray);

		// 새로운 기능은 배열 생성자 참조를 사용할 수 있게 되었다.
		String[] result2 = strings.toArray(String[]::new);

		/**
		 * Predicate 간단 기능 추가
		 */
		List<String> strings2 = List.of("A", "  ", "  ");

		// 주어진 조건을 반대로 전환할 수 있는 기능
		// 조절할 수 없는 메서드 조건을 반대로 적용해야 할 때 유용
		List<String> result3 = strings2.stream()
			.filter(Predicate.not(String::isBlank))
			.collect(Collectors.toList());

		/**
		 * Files 클래스 기능 추가
		 */
		// 파일 내용 전체를 문자열로 읽어 들이거나, 문자열 전체를 파일에 써야 할 때
		// file <-> String
		var path = Paths.get(Paths.get("").toAbsolutePath() + "/xxx-api/test.txt");
		String fileStr = Files.readString(path);
		System.out.println(fileStr);

		/**
		 * 새로운 Http Client 추가
		 */
		// 자바를 이용해 Http 요청을 주고 받아야 할 떄 사용할 수 있는 API
		// 기존에 있던 Http Client 가 있었다! (HttpUrlConnection) - 25년전 개발
		// -> HTTP/2.0, HTTP/1.1 지원이 불가능 / 비동기 처리 불가능 / 사용하거나 유지보수하는 것이 매우 어려움

		// 새로운 Http Client
		// 1) HTTP/2.0, HTTP/1.1 지원
		// 2) WebSocket 지원
		// 3) CompletableFuture 를 이용한 비동기 메커니즘 지원
		// 4) 람다와 같은 새로운 언어 시스템에 우호적

		var client = HttpClient.newHttpClient();
		// var request = HttpRequest.newBuilder(URI.create("https://postman-echo.com/get"))
		// 	.GET()
		// 	.build();

		var request = HttpRequest.newBuilder(URI.create("https://postman-echo.com/post"))
			.POST(HttpRequest.BodyPublishers.ofString("{\"num\": 1}"))
			.build();

		// HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		// printlnWithThread("response.statusCode() = " + response.statusCode());
		// printlnWithThread("response.body() = " + response.body());

		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenAccept((response) -> {
				printlnWithThread("response.statusCode() = " + response.statusCode());
				printlnWithThread("response.body() = " + response.body());
			});

		client.close();

		/**
		 * 알아두면 좋은 추가적인 변경 내용
		 */
		// ZGC(experimental) 최초 공개
		// G1GC 보다 훨씬 큰 애플리케이션을 대상으로 하고 있다.
	}

	private static void printlnWithThread(Object obj) {
		System.out.printf("%s %s\n", Thread.currentThread().getName(), obj);
	}
}
