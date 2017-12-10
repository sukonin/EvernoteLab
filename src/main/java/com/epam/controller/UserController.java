package com.epam.controller;


import com.epam.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;


  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  public String list(Model model) {
    model.addAttribute("users", userService.getAll());
    return "users/list";

  }

}
