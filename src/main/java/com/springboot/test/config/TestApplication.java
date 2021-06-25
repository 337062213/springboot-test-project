package com.springboot.test.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import com.spring4all.swagger.EnableSwagger2Doc;

@EnableSwagger2Doc
@ComponentScan(value="com.springboot.test.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan(value="com.springboot.test.mapper")
public class TestApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}