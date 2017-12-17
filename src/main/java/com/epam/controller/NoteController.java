package com.epam.controller;

import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.SessionData;
import com.epam.model.User;
import com.epam.model.Tag;
import com.epam.services.impl.NoteService;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.UserService;
import java.sql.Date;
import java.util.List;
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
public class NoteController {


  private final NotebookService notebookService;
  private final NoteService noteService;
  private final UserService userService;
  private final SessionData sessionData;

  @Autowired
  public NoteController(NotebookService notebookService,
      NoteService noteService, UserService userService,
      SessionData sessionData) {
    this.notebookService = notebookService;
    this.noteService = noteService;
    this.userService = userService;
    this.sessionData = sessionData;
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notes/{title}")
  public List<Note> getNoteByTitle(@PathVariable String title) {

    User user = sessionData.getUser();
    List<Note> byTitle = noteService.getByTitle(title, user);

    if (byTitle == null) {
      throw new RuntimeException("Ничего не найдено!");
    }
    return byTitle;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notes/{status}")
  public List<Note> getByStatus(@PathVariable Boolean status) {

    User user = sessionData.getUser();
    List<Note> byTitle = noteService.getByStatus(status, user);

    if (byTitle == null) {
      throw new RuntimeException("Ничего не найдено!");
    }
    return byTitle;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notes/{content}")
  public List<Note> getByContent(@PathVariable String content) {

    User user = sessionData.getUser();
    List<Note> byTitle = noteService.getByContent(content, user);

    if (byTitle == null) {
      throw new RuntimeException("Ничего не найдено!");
    }
    return byTitle;
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notes/{date}")
  public List<Note> getByDate(@PathVariable Date date) {

    User user = sessionData.getUser();
    List<Note> byTitle = noteService.getNotesByDate(date, user);

    if (byTitle == null) {
      throw new RuntimeException("Ничего не найдено!");
    }
    return byTitle;
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notes")
  public List<Note> getAllNotes() {
    User user = sessionData.getUser();

    List<Note> all = noteService.getAll(user);
    if (all == null) {
      throw new RuntimeException("Ничего не найдено!");
    }

    return all;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "notes/notebook")
  public List<Note> getAllNotesByNotebook(@RequestBody Notebook notebook) {
    return noteService.getAllNotesByNotebook(notebook);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "notes/{id}/tag")
  public void addTagToNote(@PathVariable String id, @RequestBody Tag tag) {
    User user = sessionData.getUser();
    noteService.addTagToNote(tag, id, user);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "notes/{note_id}/{tag_id}")
  public void deleteTagFromNote(@PathVariable("note_id") String id,
      @PathVariable("tag_id") String tag_id) {

    noteService.removeTagFromNote(Long.valueOf(id), Long.valueOf(tag_id));

  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{tag}/notes")
  public List<Note> getAllNotesByTag(@PathVariable("tag") String tag) {

    User user = sessionData.getUser();
    return noteService.getAllNotesByTag(tag, user);

  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "notes/{id}")
  public void deleteNode(@PathVariable("id") String id) {

    noteService.delete(Long.valueOf(id));

  }


}
