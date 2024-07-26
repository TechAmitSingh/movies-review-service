package com.verizon.in;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MoviesReviewServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoviesReviewServiceApplication.class, args);
		log.info("info :- "+args);
		System.out.println("Hey Rana. I am in movie reviwe services:-");
	}

}
