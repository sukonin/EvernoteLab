package com.epam.controller;

import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.SessionData;
import com.epam.model.User;
import com.epam.services.impl.NoteService;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class NotebookController {

  private final NotebookService notebookService;
  private final NoteService noteService;
  private final UserService userService;
  private final SessionData sessionData;


  @Autowired
  public NotebookController(NotebookService notebookService,
      NoteService noteService, UserService userService,
      SessionData sessionData) {
    this.notebookService = notebookService;
    this.noteService = noteService;
    this.userService = userService;
    this.sessionData = sessionData;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notebooks")
  public List<Notebook> getAllNotebookByUser() {
    User user = userService.getByEmail(sessionData.getUser().getEmail());

    if (user == null) {
      throw new RuntimeException("User not found with email " + sessionData.getUser().getEmail());
    }
    return notebookService.getAllNotebookByUser(user);
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notebooks/{title}")
  public Notebook getNotebookByTitle(@PathVariable("title") String title) {

    Notebook byTitle = notebookService.getByTitle(title, sessionData.getUser().getEmail());

    if (byTitle == null) {
      throw new RuntimeException();
    }

    return byTitle;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/notebooks")
  public void createNotebook(@RequestBody @Valid Notebook notebook) {

    if (notebookService.getByTitle(notebook.getTitle(), sessionData.getUser().getEmail()) != null) {
      throw new RuntimeException(
          "Notebook found with title " + notebook.getTitle() + ". Cannot create!");
    }
    notebook.setUser(sessionData.getUser());
    notebookService.saveOrUpdate(notebook);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/notebooks")
  public void addNoteToNotebook(@RequestParam String notebook,
      @RequestBody @Valid Note note) {
    Notebook byTitle = notebookService
        .getByTitle(notebook, sessionData.getUser().getEmail());

    note.setNotebook(byTitle);
    noteService.saveOrUpdate(note);
    notebookService.saveOrUpdate(byTitle);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/notebooks/{title}")
  public void deleteNotebookByTitle(@PathVariable("title") String title) {
    Notebook byTitle = notebookService
        .getByTitle(title, sessionData.getUser().getEmail());

    notebookService.delete(byTitle.getId());

  }

}
