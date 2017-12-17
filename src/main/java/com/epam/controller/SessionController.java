package com.epam.controller;

import com.epam.model.SessionData;
import com.epam.model.User;
import com.epam.services.impl.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SessionController {

  private final UserService userService;
  private final SessionData sessionData;

  @Autowired
  public SessionController(UserService userService, SessionData sessionData) {
    this.userService = userService;
    this.sessionData = sessionData;
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/login")
  public void login(@RequestParam String email, @RequestParam String password) {

    email = "1@epam.com";
    User user = userService.getByEmail(email);

    sessionData.setUser(user);
    if (user != null && user.getPassword().equals(password)) {
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/logout")
  public void logout() {
    sessionData.setUser(null);
  }

}
