package kr.spring.ch02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	/*
	 * [형식]
	 * 1.@RequestParam(요청파라미터네임)메서드의 인자(파라미터)
	 * 2.요청파타미터명과 호출메서드의 인자명이 같으면 오청파라미터명 생략 가능
	 * 	 @RequestParam 메서드의 인자명
	 * 	 요청파타미터를 필수적으로 사용하지 않으면 오류 발생
	 * 	 아래와 같이 required는 false로 지정하면 요청파라미터가 없어도 오류가 발생하지 않음
	 * 	 @RequestParam(value="query",required=false)
	 * 	 @RequestParam(required=false)
	 * 3.@RequestParam 생략 가능
	 * 	 요청파타미터명과 호출메서드의 인자명을 동일하게 표기
	 * 	 요청파타미터를 필수적으로 사용하지 않아도 오류가 발생하지 않음
	 */
	//요청 URL 과 실행 메서드를 연결
	@RequestMapping("/search/internal.do")
	public ModelAndView searchInternal(@RequestParam(required=false) String query) {
		
		System.out.println("query = " + query);
		
								//뷰 이름 지정
		return new ModelAndView("search/internal");
		
	}
	@RequestMapping("/search/external.do")
	public ModelAndView searchExternal(String query,@RequestParam(value="p",defaultValue="1") int pageNumber) {
		
		System.out.println("query =" + query);
		System.out.println("p = " + pageNumber);
									//뷰 이름 
		return new ModelAndView("search/external");
	}
}
