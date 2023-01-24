package com.lms.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LmsApplication {

	@Autowired
	private TableCreator tableCreator;

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
