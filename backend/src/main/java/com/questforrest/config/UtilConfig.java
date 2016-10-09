package com.questforrest.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

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
    public DispatcherServletBeanPostProcessor dispatcherServletBeanPostProcessor() {
        return new DispatcherServletBeanPostProcessor();
    }

    private class DispatcherServletBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof DispatcherServlet) {
                ((DispatcherServlet) bean).setDispatchOptionsRequest(true);
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            return bean;
        }
    }
}
