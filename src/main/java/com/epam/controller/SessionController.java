package com.epam.controller;


import com.epam.exception.UserException;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

  @GetMapping("/success")
  public String success() {
    return "Success!!!";
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/logout")
  public String logout(HttpSession session) {
    SecurityContextHolder.clearContext();
    session.invalidate();
    return "Logout success!";
  }

  @GetMapping("/error")
  @ResponseBody
  public String error() {
    throw new UserException("Example Error Handler!");
  }
}
