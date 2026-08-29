package com.registrationandloginspring.controller;

import com.registrationandloginspring.exception.InvalidCredentialsException;
import com.registrationandloginspring.mapper.UserMapper;
import com.registrationandloginspring.model.dto.LoginRequest;
import com.registrationandloginspring.model.dto.RegistrationRequest;
import com.registrationandloginspring.model.dto.UserResponse;
import com.registrationandloginspring.repository.UserRepository;
import com.registrationandloginspring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final UserService userService;

  public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, UserMapper userMapper, UserService userService) {

    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/auth/register")
  public UserResponse registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
    return userService.register(registrationRequest);
  }

  @PostMapping("/auth/login")
  public UserResponse login(@Valid @RequestBody LoginRequest loginRequest,
                            HttpServletRequest request,
                            HttpServletResponse response) {
    try {
      Authentication unauthenticated = new UsernamePasswordAuthenticationToken(
              loginRequest.email(), loginRequest.password());
      Authentication authenticated = authenticationManager.authenticate(unauthenticated);

      SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authenticated);
      SecurityContextHolder.setContext(context);
      securityContextRepository.saveContext(context, request, response);

      var user = userRepository.findByEmail(loginRequest.email()).orElseThrow();
      return userMapper.toResponse(user);

    } catch (AuthenticationException e) {
      throw new InvalidCredentialsException();
    }
  }
}