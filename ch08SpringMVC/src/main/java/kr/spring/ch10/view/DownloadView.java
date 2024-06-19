package kr.spring.ch10.view;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//다운로드 하는 파일의 경로 정보가 저장된 File 객체 반환
		File file = (File)model.get("downloadFile");
		//컨텐트 타입 지정
		response.setContentType(getContentType());
		//컨텐트의 용량 지정
		response.setContentLength((int)file.length());
		
	}
	
}
