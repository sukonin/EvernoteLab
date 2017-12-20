package com.epam.controller;

import com.epam.model.Notebook;
import com.epam.model.SessionData;
import com.epam.model.User;
import com.epam.services.impl.NotebookService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
  private final SessionData sessionData;


  @Autowired
  public NotebookController(NotebookService notebookService,
      SessionData sessionData) {
    this.notebookService = notebookService;
    this.sessionData = sessionData;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notebooks/{id}")
  public Notebook getById(@PathVariable("id") Long id) {
    System.err.println(sessionData.getUser());

    return notebookService.getById(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notebooks")
  public List<Notebook> getAllNotebookByUser() {
    System.err.println(sessionData.getUser());

    return notebookService.getAllNotebookByUser(sessionData.getUser());
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/notebooks")
  public void createNotebook(@RequestBody Notebook notebook) {
    System.err.println(sessionData.getUser());

    notebook.setUser(sessionData.getUser());
    notebookService.saveOrUpdate(notebook);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/notebooks/{id}")
  public void deleteNotebookById(@PathVariable("id") Long id) {
    System.err.println(sessionData.getUser());

    notebookService.delete(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/notebooks/{id}")
  public void updateNotebook(@PathVariable("id") Long id, @RequestBody Notebook notebook) {
    System.err.println(sessionData.getUser());

    notebook.setId(id);
    notebook.setUser(sessionData.getUser());
    notebookService.saveOrUpdate(notebook);
  }


}
