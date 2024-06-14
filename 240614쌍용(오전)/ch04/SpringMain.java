package kr.spring.ch04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//컨테이너에 DI 생성자 설정방식으로 생성된 객체(의존하고 있는)를 읽어옴
		WriteArticleService articleService = (WriteArticleService)context.getBean("writeArticleService");
		
		articleService.write();
		
		//application 종료시 컨테이너에 존재하는 모든 빈(객체)를 종료
		context.close();
	}
}
