package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
public class TestExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestExcelApplication.class, args);
	}

}
