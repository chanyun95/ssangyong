package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    
    private LoginCheckInterceptor loginCheck;
    private WriterCheckInterceptor writerCheck;
    
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
        // LoginCheckInterceptor 설정
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
        
        // WriterCheckInterceptor 설정
        registry.addInterceptor(writerCheck)
                .addPathPatterns("/board/update")
                .addPathPatterns("/board/delete");
    }
    
    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();
        // XML 설정 파일 경로 지정
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
        final TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }

}
