package com.epam.controller;

import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notebooks")
public class NotebookController {

  private final NotebookService notebookService;
  private final UserService userService;

  @Autowired
  public NotebookController(NotebookService notebookService,
      UserService userService) {
    this.notebookService = notebookService;
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{email}")
  public List<Notebook> getAllNotebooks(@PathVariable("email") String email) {

    User user = userService.getByEmail(email);

    if (user == null) {
      throw new RuntimeException("User not found with email " + email);
    }
    return notebookService.getAllNotebookByUser(user);
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{email}/{title}")
  public Notebook getNotebookByTitle(@PathVariable("title") String title,
      @PathVariable("email") String email) {

    Notebook byTitle = notebookService.getByTitle(title, email);

    if (byTitle == null) {
      throw new RuntimeException();
    }

    return byTitle;
  }




}
