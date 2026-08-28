package com.registrationandloginspring.controller;

import com.registrationandloginspring.model.User;
import com.registrationandloginspring.model.dto.RegistrationRequest;
import com.registrationandloginspring.service.UserService;
import jakarta.validation.Valid;
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
  public User registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
    return userService.save(registrationRequest);
  }
}
