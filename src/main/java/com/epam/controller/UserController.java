package com.epam.controller;


import com.epam.model.User;
import com.epam.services.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/users")
  public List<User> getAllUsers() {
    return userService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/users")
  public void createUser(@RequestBody @Valid User user) {

    if (userService.getByEmail(user.getEmail()) != null) {
      throw new RuntimeException(
          "User found with username " + user.getUsername() + ". Cannot create!");
    }
    userService.saveOrUpdate(user);
  }

}
