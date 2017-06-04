package com.beardream.config;

import com.beardream.Utils.Constants;
import com.beardream.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by soft01 on 2017/5/7.
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**")
                .excludePathPatterns(Constants.LOGIN_URL)
                .excludePathPatterns(Constants.FILE_URL);
        super.addInterceptors(registry);
    }


}
