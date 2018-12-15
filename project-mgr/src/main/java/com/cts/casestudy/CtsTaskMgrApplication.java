package com.cts.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.cts.casestudy.entities")
public class CtsTaskMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtsTaskMgrApplication.class, args);
	}
}
