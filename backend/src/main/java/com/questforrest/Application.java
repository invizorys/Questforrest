package com.questforrest;

import com.questforrest.config.DataConfig;
import com.questforrest.config.UtilConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by root on 08.10.16.
 */
@SpringBootApplication
@ComponentScan
@PropertySource("classpath:application.properties")
@Import({DataConfig.class, UtilConfig.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
