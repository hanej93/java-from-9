package org.domain.lec12;

public class Main {
	record Point(double x, double y) {

	}

	record Line(Point p1, Point p2) {

	}

	public static void findDistanceIfPoint(Object object) {
		// if (object instanceof Point p) {
		// 	double distance = Math.hypot(p.x(), p.y());
		// 	System.out.printf("원점으로부터의 거리는 %.3f입니다.\n", distance);
		// }

		// record pattern matching (instanceof pattern matching)
		if (object instanceof Point(var x, var y)) {
			double distance = Math.hypot(x, y);
			System.out.printf("원점으로부터의 거리는 %.3f입니다.\n", distance);
		}

		if (object instanceof Line(Point(var x1, var y1), Point(var x2, var y2))) {
			double distance = Math.hypot(x2 - x1, y2 - y1);
			System.out.printf("두 점 사이의 거리는 %.3f입니다.\n", distance);
		}
	}

	public static void main(String[] args) {
		Animal cat = new Cat();
		System.out.println(Main.sound(cat));
		System.out.println(Main.sound(null));
	}

	// Dog 인데 짖지 않는 강아지 case
	// 그외
	public static String sound(Animal animal) {
		return switch (animal) {
			// case Animal a when Math.random() >= 0 -> "";
			case Dog dog when dog.isQuite() -> "";
			case Dog dog -> dog.bark();
			case Cat cat -> cat.purr();
			case null -> "";
		};
	}
}
