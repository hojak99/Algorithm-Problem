package chapter_2.quiz_2_1;

import chapter_2.Apple;

public class PrettyWonderful implements PrettyPredicate {

	@Override
	public String process(Apple apple) {
		return "��� ���� : " + apple.getCount();
	}
	
}
