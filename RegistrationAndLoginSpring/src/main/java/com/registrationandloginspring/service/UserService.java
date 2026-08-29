package com.registrationandloginspring.service;

import com.registrationandloginspring.exception.InvalidCredentialsException;
import com.registrationandloginspring.mapper.UserMapper;
import com.registrationandloginspring.model.User;
import com.registrationandloginspring.model.dto.LoginRequest;
import com.registrationandloginspring.model.dto.RegistrationRequest;
import com.registrationandloginspring.model.dto.UserResponse;
import com.registrationandloginspring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserResponse register(RegistrationRequest registrationRequest) {
    User user = userMapper.toUser(registrationRequest);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

  public Long login(LoginRequest loginRequest) {
    User user = userRepository.findByEmail(loginRequest.email())
            .orElseThrow(InvalidCredentialsException::new);

    if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
      throw new InvalidCredentialsException();
    }

    return user.getId();
  }

}
