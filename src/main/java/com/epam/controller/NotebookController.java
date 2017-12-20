package com.epam.controller;

import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
  @GetMapping(value = "/notebooks/{id}")
  public Notebook getById(@PathVariable("id") Long id) {
    return notebookService.getById(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notebooks")
  public List<Notebook> getAllNotebookByUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByEmail(principal.toString());

    return notebookService.getAllNotebookByUser(user);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/notebooks")
  public void createNotebook(@RequestBody Notebook notebook) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByEmail(principal.toString());
    notebook.setUser(user);

    notebookService.saveOrUpdate(notebook);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/notebooks/{id}")
  public void deleteNotebookById(@PathVariable("id") Long id) {
    notebookService.delete(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/notebooks/{id}")
  public void updateNotebook(@PathVariable("id") Long id, @RequestBody Notebook notebook) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByEmail(principal.toString());

    notebook.setId(id);
    notebook.setUser(user);

    notebookService.saveOrUpdate(notebook);
  }


}
