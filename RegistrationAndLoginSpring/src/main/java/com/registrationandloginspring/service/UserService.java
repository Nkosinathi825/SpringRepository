package com.registrationandloginspring.service;

import com.registrationandloginspring.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  public Result<User> save(User user){
    Result<User> validationResult = validateUser(user);

    if(validationResult.isSuccessful()){
      user.setId(10L);
    }
    return validationResult;
  }

  private Result<User> validateUser(User user){

    List<ValidationError> errors = new ArrayList<>();

    if(user.getName().length()<5){
      errors.add(new ValidationError("name","name must contain at least 5 characters"));
    }
    if(user.getSurname().length()<5){
      errors.add(new ValidationError("name","surname must contain at least 5 characters"));
    }

    if(!errors.isEmpty()){
      return Result.failure(errors);
    }

    return Result.success(user);
  }
}
