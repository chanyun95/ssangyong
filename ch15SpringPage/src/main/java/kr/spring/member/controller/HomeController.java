package kr.spring.member.controller;

import java.net.http.HttpRequest;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	private MemberService memberService;
	
	KakaoAPI kakaoApi = new KakaoAPI();
		
	@RequestMapping(value="/member/kakaologin")
	public ModelAndView login(@RequestParam("code") String code,HttpSession session) throws AuthCheckException {
		ModelAndView mav = new ModelAndView();
		//1번 인증코드 요청 전달
		String accessToken = kakaoApi.getAccessToken(code);
		//2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		
		System.out.println("login info : " + userInfo.toString());
		
		if(userInfo.get("email") != null) {
			session.setAttribute("id", userInfo.get("id").toString());
			session.setAttribute("email", userInfo.get("email").toString());
			session.setAttribute("name", userInfo.get("nickname").toString());
			session.setAttribute("profile", userInfo.get("profile"));
			session.setAttribute("accessToken", accessToken);
		}
		//3번 DB에서 해당 사용자 정보가 있는지 확인
		MemberVO userExists = memberService.checkUser(userInfo.get("id").toString());
		//DB에 해당 이메일로 등록된 사용자가 없으면 회원가입 폼으로 이동
		if (userExists == null) {
		    mav.setViewName("redirect:/member/registerKakao");
		    return mav;
		}
		MemberVO member = null;
		member = memberService.selectCheckMember(userInfo.get("id").toString());
		
	    session.setAttribute("user", member);
	    
	    mav.setViewName("redirect:/main/main");
	    return mav;
	}
	@GetMapping("/member/registerKakao")
	public String kakaoform(Model model, HttpSession session) {
	    String id = (String) session.getAttribute("id");
	    String name = (String) session.getAttribute("name");
	    String email = (String) session.getAttribute("email");
	    
	    MemberVO memberVO = new MemberVO();
	    memberVO.setId(id);
	    memberVO.setName(name);
	    memberVO.setEmail(email);
	    
	    model.addAttribute("memberVO", memberVO);

	    return "registerKakao";
	}
	@PostMapping("/member/registerKakao")
	public String kakaoSubmit(@Valid MemberVO memberVO,BindingResult result,Model model,HttpServletRequest request) {
		log.debug("<<회원가입>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "registerKakao";
		}
		//회원가입
		memberService.insertMember(memberVO);
		//UI 메시지 처리
		model.addAttribute("accessTitle","회원 가입");
		model.addAttribute("accessMsg","회원 가입이 완료되었습니다.");
		model.addAttribute("accessBtn","홈으로");
		model.addAttribute("accessUrl",request.getContextPath()+"/main/main");
		
		return "common/resultView";
	}
	
	@RequestMapping(value="/member/kakaologout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("id");
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.removeAttribute("profile");
		session.removeAttribute("user");
		
		mav.setViewName("redirect:/main/main");
		return mav;
	}
}