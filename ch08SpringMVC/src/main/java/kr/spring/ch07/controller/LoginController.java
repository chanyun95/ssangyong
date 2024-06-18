package kr.spring.ch07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch07.validator.LoginValidator;
import kr.spring.ch07.vo.LoginVO;

@Controller
public class LoginController {
	//유효성 체크를 위한 자바빈 초기화
	@ModelAttribute
	public LoginVO initCommand() {
		return new LoginVO();
	}
	//폼 호출
	@GetMapping("/login/login.do")
	public String form() {
		return "login/form";
	}
	//폼에서 전송된 데이터 처리
	@PostMapping("/login/login.do")
	public String submit(LoginVO loginVO,BindingResult result) {
		//유효성 체크
		new LoginValidator().validate(loginVO, result);
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {//에러가 있을 경우 폼을 다시 호출
			return form();
		}
		//로그인 성공
		return "redirect:/index.jsp";
	}
}
