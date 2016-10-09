package com.questforrest.config;

import org.apache.catalina.filters.CorsFilter;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by root on 08.10.16.
 */
@Configuration
public class UtilConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new AbstractConverter<Date, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(Date source) {
                if (source == null) return null;
                return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
            }
        });
        modelMapper.addConverter(new AbstractConverter<LocalDateTime, Date>() {
            @Override
            protected Date convert(LocalDateTime source) {
                if (source == null) return null;
                return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
            }
        });
        return modelMapper;
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("cors.allowed.headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        registration.setName("CorsFilter");
        return registration;
    }

    @Bean("corsFilter")
    public Filter corsFilter() {
        return new CorsFilter();
    }
}
