package com.example.PollSysProject.PollService.PollSysProject.PollService.Client;


import feign.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8083", configuration =com.example.PollSysProject.PollService.PollSysProject.PollService.config.FeignConfig.class)
public interface client {
    @GetMapping("/users/{id}")
    Client getUserById(@PathVariable Long id);
}
