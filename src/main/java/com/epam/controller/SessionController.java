package com.epam.controller;


import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {


  @GetMapping("/success")
  public String success() {
    return "Success!!!";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/logout")
  public void logout(HttpSession session) {
    session.invalidate();
  }

}
