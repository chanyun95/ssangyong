package kr.spring.ch18;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnot.xml");
		
		HomeController home = (HomeController)context.getBean("homeController");
		
		System.out.println(home);
		
		context.close();
	}
}
