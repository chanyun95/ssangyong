package kr.spring.member.conroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	//로그 처리(로그 대상 지정)
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	/*=============================
	 * 회원가입
	 ============================*/
	//자바빈(VO) 초기화(커스텀 태그 사용할 경우 필수)
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//회원가입 폼 호출
	@GetMapping("/member/registerUser")
	public String form() {
		return "memberRegister";//Tiles 설정명
	}
	//전송된 데이터 처리
	@PostMapping("/member/registerUser")
	public String submit(@Valid MemberVO memberVO,BindingResult result,Model model,
													HttpServletRequest request) {
		log.debug("<<회원가입>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
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
	/*=============================
	 * 회원로그인
	 ============================*/
	//로그인 폼 호출
	@GetMapping("/member/login")
	public String formLogin() {
		return "memberLogin";
	}
	// 로그인 폼에서 전송된 데이터 처리
	@PostMapping("/member/login")
	public String submitLogin(@Valid MemberVO memberVO,BindingResult result,HttpSession session,
								HttpServletResponse response) {
		log.debug("<<회원로그인>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		//로그인 체크(id,passwd 일치 여부 체크)
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(memberVO.getId());
			boolean check = false;
			if(member!=null) {
				//비밀번호 일치여부 체크
				check = member.ischechedPassword(memberVO.getPasswd());
			}
			if(check) {//인증 성공
				//====== 자동로그인 체크 시작 =======//
				//====== 자동로그인 체크 끝 =======//
				//로그인 처리  
				session.setAttribute("user", member);
				
				log.debug("<<인증성공>>");
				log.debug("<<id>> : " +member.getId());
				log.debug("<<auth>> : " +member.getAuth());
				log.debug("<<au_id>> : " +member.getAu_id());
				
				if(member.getAuth() == 9) {//관리자
					return "redirect:/main/admin";
				}else {
					return "redirect:/main/main";
				}
			} 
			//인증 실패
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			//인증 실패
			if(member!=null && member.getAuth()==1) {//정지회원 메시지 표시
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}
			log.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
	/*=============================
	 * 로그아웃
	 ============================*/
	@GetMapping("/member/logout")
	public String processLogout(HttpSession session) {
		//로그아웃
		session.invalidate();
		//====== 자동로그인 체크 시작 =======//
		//====== 자동로그인 체크 끝 =======//
		
		return "redirect:/main/main";
	}
	/*=============================
	 * MY페이지
	 ============================*/
	@GetMapping("/member/myPage")
	public String process(HttpSession session,Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원정보
		MemberVO member = memberService.selectMember(user.getMem_num());
		
		log.debug("<<MY페이지>> : " + member);
		
		model.addAttribute("member",member);
		
		return "myPage";
	}
	
}
