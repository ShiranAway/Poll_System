package com.example.PollSysProject.UserService.PollSysProject.UserService.UserServiceController;


import com.example.PollSysProject.UserService.PollSysProject.UserService.UserServiceModel.UserModel;
import com.example.PollSysProject.UserService.PollSysProject.UserService.User_Service_logic.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private User_Service user_service;

    @PostMapping
    public ResponseEntity<?> createOrUpdateUser(@RequestBody UserModel user){
        try {
            UserModel savedUser = user_service.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user:"+e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            List<UserModel>users =user_service.getAllUsers();
            return ResponseEntity.ok(users);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erroe fetching"+e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            Optional<UserModel> user = user_service.getUserById(id);
            if (user.isPresent()){
                return ResponseEntity.ok(user.get());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not fiund with id:"+id);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erroor fetching User:"+ e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            user_service.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting the user:"+e.getMessage());
        }
    }
}
