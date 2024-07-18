package kr.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.spring.interceptor.AutoLoginCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;
import kr.spring.websocket.SocketHandler;

//자바코드 기반 설정 클래스
@Configuration
@EnableWebSocket
public class AppConfig implements WebMvcConfigurer,
                                       WebSocketConfigurer{
	private AutoLoginCheckInterceptor autoLoginCheck;
	private LoginCheckInterceptor loginCheck;
	private WriterCheckInterceptor writerCheck;
	
	@Bean
	public AutoLoginCheckInterceptor interceptor() {
		autoLoginCheck = new AutoLoginCheckInterceptor();
		return autoLoginCheck;
	}
	
	@Bean
	public LoginCheckInterceptor interceptor2() {
		loginCheck = new LoginCheckInterceptor();
		return loginCheck;
	}
	
	@Bean
	public WriterCheckInterceptor interceptor4() {
		writerCheck = new WriterCheckInterceptor();
		return writerCheck;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//AutoLoginCheckInterceptor 설정
		registry.addInterceptor(autoLoginCheck)
		        .addPathPatterns("/**")
		        .excludePathPatterns("/images/**")
		        .excludePathPatterns("/image_upload/**")
		        .excludePathPatterns("/upload/**")
		        .excludePathPatterns("/css/**")
		        .excludePathPatterns("/js/**")
		        .excludePathPatterns("/member/login")
		        .excludePathPatterns("/member/logout")
		        .excludePathPatterns("/member/registerUser")
		        .excludePathPatterns("/member/kakaologin")
		        .excludePathPatterns("/member/kakaologout")
		        .excludePathPatterns("/member/registerKakao");
		//LoginCheckInterceptor 설정
		registry.addInterceptor(loginCheck)
		        .addPathPatterns("/member/myPage")
		        .addPathPatterns("/member/update")
		        .addPathPatterns("/member/changePassword")
		        .addPathPatterns("/member/delete")
		        .addPathPatterns("/board/write")
		        .addPathPatterns("/board/update")
		        .addPathPatterns("/board/delete")
		        .addPathPatterns("/talk/talkRoomWrite")
		        .addPathPatterns("/talk/talkList")
		        .addPathPatterns("/talk/talkDetail");
		//WriterCheckInterceptor 설정
		registry.addInterceptor(writerCheck)
		        .addPathPatterns("/board/update")
		        .addPathPatterns("/board/delete");
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = 
				                  new TilesConfigurer();
		//XML 설정 파일 경로 지정
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/member.xml",
				"/WEB-INF/tiles-def/board.xml",
				"/WEB-INF/tiles-def/talk.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = 
				                  new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
	@Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
    	Properties prop = new Properties();
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.transport.protocol", "smtp");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.debug", "true");
    	
    	JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
    	javaMail.setHost("smtp.gmail.com");
    	javaMail.setPort(587);
    	javaMail.setDefaultEncoding("utf-8");
    	javaMail.setUsername("dragonyxit@gmail.com");
    	javaMail.setPassword("zgnwhuaalygaqyfm");
    	javaMail.setJavaMailProperties(prop);
    	return javaMail;
    }
	
	//웹소켓 셋팅
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(), "message-ws")
		        .setAllowedOrigins("*");
	}
}







