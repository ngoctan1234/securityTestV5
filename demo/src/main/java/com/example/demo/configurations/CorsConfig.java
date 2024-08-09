package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**");

//                registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
//                        .allowedOrigins("http://example.com") // Cho phép yêu cầu từ nguồn gốc cụ thể
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức cụ thể
//                        .allowedHeaders("Header-Name") // Cho phép các header cụ thể
//                        .allowCredentials(true); // Cho phép gửi cookie và thông tin xác thực
            }
        };
    }
}
