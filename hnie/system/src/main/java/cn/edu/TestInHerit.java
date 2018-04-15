package cn.edu;

public class TestInHerit {
	public static void main(String[] args) {
		PostGraduate d = new PostGraduate();

		System.out.println(d.grade);
	}
}

class Person {

	private int grade = 10;
}

class Student extends Person {

	protected int grade = 15;
}

class PostGraduate extends Student {

	protected int grade = 20;
}