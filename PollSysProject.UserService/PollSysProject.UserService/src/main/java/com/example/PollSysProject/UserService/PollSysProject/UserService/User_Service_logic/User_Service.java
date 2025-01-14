package com.example.PollSysProject.UserService.PollSysProject.UserService.User_Service_logic;

import com.example.PollSysProject.UserService.PollSysProject.UserService.UserServiceModel.UserModel;
import com.example.PollSysProject.UserService.PollSysProject.UserService.UserServiceRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class User_Service {

    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(UserModel user){
        try {
            return  userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("error saving user:"+e.getMessage());
        }
    }
    public List<UserModel> getAllUsers(){
        try {
            return userRepository.findAll();

        }catch (Exception e){
            throw new RuntimeException("Error fetching user:"+e.getMessage());
        }
    }

    public Optional<UserModel> getUserById(Long id){
        try {
            return userRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException("Error fetching user with ID:"+ e.getMessage());
        }
    }
    public void deleteUserById(Long id){
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error deleting user with ID:"+e.getMessage());
        }
    }
    }


