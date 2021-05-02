package com.github.hanpyo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HanpyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanpyoApplication.class, args);
	}
}
