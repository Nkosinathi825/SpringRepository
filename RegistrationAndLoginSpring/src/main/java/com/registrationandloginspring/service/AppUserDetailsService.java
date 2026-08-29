package com.registrationandloginspring.service;

import com.registrationandloginspring.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public AppUserDetailsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    var user = userRepository.findByEmail(email)
            .orElseThrow(()-> new UsernameNotFoundException("No user with email " + email ));
    System.out.println("This was called ");

    return User.withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities(Collections.emptyList())
            .build();
  }
}
