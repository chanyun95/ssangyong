package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller가 없으면 모델클래스로 인식하지 못함
@Controller
public class HelloController {
	//요청 URL 과 실행 메서드를 연결
	@RequestMapping("/hello.do")
	public ModelAndView hello() {//Model > 데이터
		ModelAndView mav = new ModelAndView();
		//뷰 이름 지정
		mav.setViewName("hello");// viewResolver에 의해 /WEB-INF/views/hello.jsp  hello 뒤에 .jsp X
		//뷰에서 사용할 데이터 세팅
		mav.addObject("greeting","안녕하세요!");
		
		return mav;
	}
}
