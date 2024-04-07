package org.domain.lec02;

import java.lang.reflect.Field;

import org.domain.Lec02Person;

public class Main {

	public static void main(String[] args) throws Exception {
		// module export 가 되어있지 않고 Open만한 경우(직접 참조가 없는 경우)
		// deep reflection 은 사용 가능하다.
		// Class<?> personClass = Class.forName("org.domain.Lec02Person");
		// Object p = personClass.getConstructor().newInstance();
		Lec02Person p = new Lec02Person();
		Class<Lec02Person> personClass = Lec02Person.class;
		Field weight = personClass.getDeclaredField("weight");
		weight.setAccessible(true);

		// 모듈이 open 되어있지 않더라도 shallow reflection 은 가능하다.
		// Field name = personClass.getDeclaredField("name");
		// name.set(p, "ABC");
		// System.out.println(p.getName());

		// 몸무게를 100으로 수정
		weight.set(p, 100);
		System.out.println("weight = " + p.getWeight());
		System.out.println(p);
	}
}
