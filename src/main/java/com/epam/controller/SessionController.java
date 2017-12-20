package com.epam.controller;

import com.epam.model.SessionData;
import com.epam.model.User;
import com.epam.services.impl.UserService;
import javax.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SessionController {

  private final SessionData sessionData;
  private final UserService userService;

  @Autowired
  public SessionController(SessionData sessionData, UserService userService) {
    this.sessionData = sessionData;
    this.userService = userService;
  }


  @GetMapping("/success")
  public String success() {
    return "Success!!!";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/logout")
  public void logout(HttpSession session) {
    sessionData.setUser(null);
    session.invalidate();
  }

}
