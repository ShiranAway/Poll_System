package com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Repository;

import com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Entity.Poll;

import org.springframework.data.jpa.repository.JpaRepository;


public  interface PollRepository extends JpaRepository<Poll, Long> {
}
