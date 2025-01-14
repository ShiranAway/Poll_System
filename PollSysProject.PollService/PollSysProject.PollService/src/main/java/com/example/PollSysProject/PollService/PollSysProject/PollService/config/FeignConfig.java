package com.example.PollSysProject.PollService.PollSysProject.PollService.config;

import feign.Client;

import feign.Request;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {




       @Bean
     public Client FeignClient() {
           return new  Client.Default(null, null);

        }


        @Bean
        public Request.Options requestOptions() {
            return new Request.Options(5000, 5000);
        }


        @Bean
        public RequestInterceptor requestInterceptor() {
            return requestTemplate -> {
                requestTemplate.header("Accept", "application/json");
                requestTemplate.header("Authorization", "Bearer dummy-token");
            };
        }

        }


