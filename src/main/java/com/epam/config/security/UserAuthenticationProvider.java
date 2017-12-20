package com.epam.config.security;

import com.epam.model.User;
import com.epam.services.impl.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

  private final UserService userService;


  @Autowired
  public UserAuthenticationProvider(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    User user = userService.getByEmail(authentication.getName());

    if (user == null) {
      throw new BadCredentialsException("User not found");
    }

    String password = (String) authentication.getCredentials();

    boolean access = new BCryptPasswordEncoder().matches(password, user.getPassword());
    if (!access) {
      throw new BadCredentialsException("Wrong password");
    }


    return new UsernamePasswordAuthenticationToken(user.getEmail(), password, new ArrayList<>());
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(
        UsernamePasswordAuthenticationToken.class);
  }
}
