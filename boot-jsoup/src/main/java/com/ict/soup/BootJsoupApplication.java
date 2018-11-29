package com.ict.soup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("conf/ex.properties")
public class BootJsoupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJsoupApplication.class, args);
	}
}
