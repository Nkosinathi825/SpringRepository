package com.registrationandloginspring.controller;

import com.registrationandloginspring.model.User;
import com.registrationandloginspring.service.Result;
import com.registrationandloginspring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Result<User>> registerUser(@RequestBody User user){
    Result<User> userResult = userService.save(user);
    if(userResult.isSuccessful()){
      return  ResponseEntity.ok(userResult);
    }

    return ResponseEntity.badRequest().body(userResult);
  }
}
