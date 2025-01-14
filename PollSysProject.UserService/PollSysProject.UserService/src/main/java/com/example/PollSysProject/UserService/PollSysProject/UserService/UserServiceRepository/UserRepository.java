package com.example.PollSysProject.UserService.PollSysProject.UserService.UserServiceRepository;

import com.example.PollSysProject.UserService.PollSysProject.UserService.UserServiceModel.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
