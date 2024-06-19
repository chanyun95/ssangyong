package kr.spring.ch09.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch09.vo.MemberVO;

@Controller
public class MemberWriteController {
	//유효성 체크를 위한 자바빈(VO) 초기화
	@ModelAttribute("command")
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//폼 호출
	@GetMapping("/member/write.do")
	public String form() {
		return "member/write";
	}
	//폼에서 전송된 데이터 처리
	@PostMapping("/member/write.do")
	public String submit(@ModelAttribute("command") @Valid MemberVO vo,BindingResult result) {
		System.out.println("전송된 데이터 : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "member/write";
		}
		
		return "redirect:/index.jsp";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//true 이면 요청 파라미터의 값이 null 이거나 빈문자열("")일 때 변환 처리를 하지 않고 null 값으로 할당
		//false 라면 변환 과정에서 에러가 발생하고 에러코드로 "typeMismatch"가 추가 됨
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
	}
}
