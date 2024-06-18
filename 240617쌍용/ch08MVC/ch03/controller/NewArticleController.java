package kr.spring.ch03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch03.service.NewArticleService;
import kr.spring.ch03.vo.NewArticleVO;

@Controller
public class NewArticleController {
	@Autowired
	private NewArticleService newArticleService;
	
	//GET 요청이 들어올 때 호출
	@GetMapping("/article/newArticle.do")
	public String form() {
				//뷰 이름 지정
		return "article/newArticleForm";
	}
	/*
	 * @ModelAttribute 어노테이션을 이용해서 전송된 데이터를 자바빈에 담기
	 * [기능]
	 * 1. 자바빈(VO) 생성
	 * 2. 전송된 데이터를 자바빈에 저장
	 * 3. View에서 사용할 수 있도록 request에 자바빈(VO)를 저장
	 * [형식]
	 * 1.@ModelAttribute(속성명) NewArticleVO vo
	 * 	 지정한 속성명으로 JSP에서 request에 접근해서 자바빈(VO) 호출 가능.
	 * 	 예) ${속성명.title}
	 * 2.@ModelAttribute를 명시할 때 속성명을 생략할 수 있음
	 * 	 속성명을 생략하면 클래스명의 첫 글자를 소문자로 속성명을 자동 생성
	 * 	 예) ModelAttribute NewArticleVO vo
	 *		${newArticleVO.title}
	 * 3.@ModelAttribute 생략
	 * 	 호출 메서드에 인자명만 명시
	 * 	 예)NewArticleVO vo와 같이 인자명만 명시.
	 *		request에 저장되는 속성명은 newArticleVO로 저장됨
	 */
	//POST 요청이 들어올 때 호출
	@PostMapping("/article/newArticle.do")
	public String submit(NewArticleVO vo) {

		newArticleService.writeArticle(vo);
		
		return "article/newArticleSumitted";
	}
}
