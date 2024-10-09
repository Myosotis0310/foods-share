package com.group2.foodsshare.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *  跨源资源共享（CORS）过滤器
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址：允许所有源的跨域请求
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头：允许所有头信息的跨域请求
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法：允许所有 HTTP 方法的跨域请求。"*" 表示接受任何 HTTP 方法，如 GET、POST、PUT、DELETE 等
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置：将 CORS 配置应用到所有的路径（"/**"）上，这意味着整个应用程序的接口都将支持跨域请求
        return new CorsFilter(source);
    }

}
