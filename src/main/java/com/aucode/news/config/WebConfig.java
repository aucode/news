package com.aucode.news.config;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    @SuppressWarnings("unused")
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addInterceptor添加拦截的请求，excludePathPatterns并排除几个不拦截的请求
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**","/newscolumn/*")
                .excludePathPatterns("/favicon.ico","/browse","/type","/page","/","*.html","/admin.tologin","/admin" +
                                "/login",
                        "/css/*","/js/*","/fonts/*","/images/*","*.jpg","/index.search","/image/*","*.js", "*.css");
    }

}