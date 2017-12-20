package com.epam.controller;


import com.epam.model.User;
import com.epam.services.impl.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/users")
  public List<User> getAllUsers() {
    return userService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/registration")
  public void createUser(@RequestBody User user) {
    userService.saveOrUpdate(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/users/{id}")
  public void updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
      user.setId(id);
      userService.update(user);
  }

}
