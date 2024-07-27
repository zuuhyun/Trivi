package web.trivi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.CacheControl.maxAge;

// 스프링 서버 전역적으로 CORS 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
//                .allowedOrigins("http://175.45.195.88", "175.45.195.88")
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .exposedHeaders("Authorization")
                .maxAge(3000);
    }
}