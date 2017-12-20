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
public class NoteController {


  private final NotebookService notebookService;
  private final NoteService noteService;
  private final SessionData sessionData;

  @Autowired
  public NoteController(NotebookService notebookService,
      NoteService noteService,
      SessionData sessionData) {
    this.notebookService = notebookService;
    this.noteService = noteService;
    this.sessionData = sessionData;
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notes")
  public List<Note> getAllNotes() {
    return noteService.getAll(sessionData.getUser());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notes/{id}")
  public Note getNoteById(@PathVariable("id") Long id) {
    return noteService.getById(id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/notes/{id}")
  public void deleteNode(@PathVariable("id") Long id) {
    noteService.delete(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/notes")
  public void createNoteInNotebook(@RequestParam Long notebook_id, @RequestBody Note note) {

    Notebook notebook = notebookService.getById(notebook_id);
    note.setNotebook(notebook);
    noteService.saveOrUpdate(note);
    notebookService.saveOrUpdate(notebook);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/notes/{id}")
  public void updateNote(@PathVariable("id") Long id, @RequestBody Note note) {

    Note note1 = noteService.getById(id);
    note.setNotebook(note1.getNotebook());
    note.setId(id);

    noteService.saveOrUpdate(note);
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notebooks/{id}/note")
  public List<Note> getAllNotesByNotebook(@PathVariable("id") Long notebook_id) {
    Notebook notebook = notebookService.getById(notebook_id);
    return noteService.getAllNotesByNotebook(notebook);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/notes/{note_id}/{tag_id}")
  public void addTagToNote(@PathVariable("tag_id") Long tag_id,
      @PathVariable("note_id") Long note_id) {
    User user = sessionData.getUser();
    noteService.addTagToNote(tag_id, note_id, user);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/notes/{note_id}/{tag_id}")
  public void deleteTagFromNote(@PathVariable("note_id") Long id,
      @PathVariable("tag_id") Long tag_id) {

    noteService.removeTagFromNote(id, tag_id);

  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notes/tag/{tag}")
  public List<Note> getAllNotesByTag(@PathVariable("tag") String tag) {

    return noteService.getAllNotesByTag(tag, sessionData.getUser());

  }


}
