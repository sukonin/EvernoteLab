package com.epam.services.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.Tag;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
@Transactional
public class NoteServiceTest {

  @Autowired
  NoteService noteService;
  @Autowired
  TagService tagService;
  @Autowired
  NotebookService notebookService;
  @Autowired
  UserService userService;

  @Test
  public void getByTitle() throws Exception {
    assertNotNull(noteService.getByTitle("Third"));
  }

  @Test
  public void getByStatus() throws Exception {
    assertNotNull(noteService.getByStatus(true));
  }

  @Test
  public void getByNotebook() throws Exception {
    Notebook notebook = notebookService.getByTitle("Notebook for Second User");
    assertNotEquals(noteService.getAllNotesByNotebook(notebook), Collections.EMPTY_LIST);
  }

  @Test
  public void getByContent() throws Exception {
    assertNotNull(noteService.getByContent("blablaContent"));
  }

  @Test
  public void getNotesByDate() throws Exception {
    LocalDate of = LocalDate.of(2017, 12, 29);
    assertNotNull(noteService.getNotesByDate(Date.valueOf(of)));
  }

  @Test
  public void addTagToNote() throws Exception {

    Tag newTag = new Tag();
    newTag.setTag("test");
    tagService.saveOrUpdate(newTag);

    Note third = noteService.getByTitle("Third");

    noteService.addTagToNote(newTag, third);
    assertNotEquals(noteService.getAllNotesByTag(newTag), Collections.EMPTY_LIST);
  }

  @Test
  /*TODO FIX*/
  public void removeTagFromNote() throws Exception {
    Tag tag = tagService.getByTag("work");
    Note first_note = noteService.getByTitle("First Note");

    noteService.removeTagFromNote(tag, first_note);
    log.warning(first_note.getTags().toString());
  }

  @Test
  public void getAllNotesByTag() throws Exception {
    Tag tag = tagService.getByTag("work");
    assertNotEquals(noteService.getAllNotesByTag(tag), Collections.EMPTY_LIST);
  }

  @Test
  public void getAllNotesByTag1() throws Exception {
    Tag tag = tagService.getByTag("work");
    Tag tag2 = tagService.getByTag("priority");
    List<Tag> tagList = new ArrayList<>();
    tagList.add(tag);
    tagList.add(tag2);
    assertNotEquals(noteService.getAllNotesByTag(tagList), Collections.EMPTY_LIST);
  }

  @Test
  public void getAll() throws Exception {
    assertNotEquals(noteService.getAll(), Collections.EMPTY_LIST);
  }


  @Test
  public void saveOrUpdate() throws Exception {
    Note note = new Note();
    note.setTitle("test");
    Notebook notebook = notebookService.getByTitle("Notebook for Second User");
    note.setNotebook(notebook);
    noteService.saveOrUpdate(note);
    Note test = noteService.getByTitle("test");
    assertNotNull(test);

  }

  @Test
  public void ydelete() throws Exception {
    Note test = noteService.getByContent("blablaContent");
    log.warning(test.toString());
    noteService.delete(test.getId());
  }

}