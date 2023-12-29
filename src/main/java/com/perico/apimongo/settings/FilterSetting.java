package com.perico.apimongo.settings;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perico.apimongo.filters.VerifyToken;
import com.perico.apimongo.settings.auth.JwtTokenTools;

@Configuration
public class FilterSetting {
    private final JwtTokenTools jwtTokenTools;

    public FilterSetting(JwtTokenTools jwtTokenTools) {
        this.jwtTokenTools = jwtTokenTools;
    }


    @Bean // * CONFIGURE TOKEN AUTH
    FilterRegistrationBean<VerifyToken> verify() {
        FilterRegistrationBean<VerifyToken> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new VerifyToken(jwtTokenTools));
        registrationBean.addUrlPatterns("/filter");   
        return registrationBean;
    }
}
