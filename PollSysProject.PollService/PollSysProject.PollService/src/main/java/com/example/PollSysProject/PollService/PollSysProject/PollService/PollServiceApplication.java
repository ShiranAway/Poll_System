package com.example.PollSysProject.PollService.PollSysProject.PollService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//@FeignClient
@EnableFeignClients

public class PollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollServiceApplication.class, args);
	}

}
