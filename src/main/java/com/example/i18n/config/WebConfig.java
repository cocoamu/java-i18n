package com.example.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这个拦截器会拦截请求中 key 为 lang 的参数（不配置的话是 locale），这个参数则指定了当前的环境信息。
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

    /**
     * 这个实例会替换掉默认的 AcceptHeaderLocaleResolver，
     * 不同于 AcceptHeaderLocaleResolver 通过请求头来判断当前的环境信息，
     * SessionLocaleResolver 将客户端的 Locale 保存到 HttpSession 对象中，
     * 并且可以进行修改（这意味着当前环境信息，前端给浏览器发送一次即可记住，
     * 只要 session 有效，浏览器就不必再次告诉服务端当前的环境信息）。
     *
     */
    @Bean
    LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }
}
