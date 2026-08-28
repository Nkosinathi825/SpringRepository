package com.registrationandloginspring.service;

import com.registrationandloginspring.mapper.UserMapper;
import com.registrationandloginspring.model.User;
import com.registrationandloginspring.model.dto.RegistrationRequest;
import com.registrationandloginspring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  public UserService(UserMapper userMapper , UserRepository userRepository) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
  }

  public User save(RegistrationRequest registrationRequest) {
    User user = userMapper.toUser(registrationRequest);
    User savedUser = userRepository.save(user);
    return user;
  }

}