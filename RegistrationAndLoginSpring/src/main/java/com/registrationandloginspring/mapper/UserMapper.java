package com.registrationandloginspring.mapper;

import com.registrationandloginspring.model.User;
import com.registrationandloginspring.model.dto.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toUser(RegistrationRequest request) {

    User user = new User();

    user.setName(request.name());
    user.setEmail(request.email());
    user.setPassword(request.password());

    return user;
  }
}
