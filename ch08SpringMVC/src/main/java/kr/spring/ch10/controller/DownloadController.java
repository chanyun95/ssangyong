package kr.spring.ch10.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
	@RequestMapping("/file.do")
						/* servlet context를 구하기 위해 session 사용 */
	public ModelAndView download(HttpSession session) {
		//file.txt 의 컨텍스트 경로상의 절대경로를 구하기
		String path = session.getServletContext().getRealPath("/WEB-INF/file.txt");
		
		File downloadFile = new File(path);
		//클래스에서 작업하기 위해 jsp 사용X
								 //뷰 이름		속성명		  속성값
		return new ModelAndView("download","downloadFile",downloadFile);
	}
}
