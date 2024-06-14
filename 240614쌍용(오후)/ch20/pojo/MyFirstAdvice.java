package kr.spring.ch20.pojo;

public class MyFirstAdvice {
	/*
	 * 구현 가능한 Advice(언제 공통 기능을 핵심 기능에 적용할 지를 정의)
	 * 종류						설명
	 * Before Advice			대상 객체의 메서드 호출 전에 공통 기능을 실행
	 * After Returning Advice	대상 객체의 메서드가 예외 없이 실행한 이후에 공통 기능을 실행
	 * After Throwing Advice	대상 객체의 메서드를 실행하는 도중 예외가 발생한 경우에 공통 기능을 실행
	 * After Advice				대상 객체의 메서드를 실행하는 도중 예외가 발생 여부와 상관없이 메서드 실행 후 공통 기능을 실행
	 * 							(try~catch~finally 의 finally 블럭과 비슷)
	 * Around Advice			대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행
	 * 
	 */
	public void before() {
		//메서드 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! **메서드가 호출되게 전에 나온다!");
	}
}
