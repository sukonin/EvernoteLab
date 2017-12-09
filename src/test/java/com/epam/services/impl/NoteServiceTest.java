package com.epam.services.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.Note;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
public class NoteServiceTest {

  @Autowired
  NoteService noteService;
  @Autowired
  TagService tagService;

  @Test
  public void getByTitle() throws Exception {
    assertNotNull(noteService.getByTitle("First Note"));
  }

  @Test
  public void getByStatus() throws Exception {
    assertNotNull(noteService.getByStatus(true));
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
    Note note = noteService.getByTitle("First Note");
    noteService.addTagToNote(newTag, note);
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
    assertNotEquals(noteService.getAllNotesByTag(tagList), Collections.EMPTY_SET);
  }

  @Test
  public void getAll() throws Exception {
    assertNotEquals(noteService.getAll(), Collections.EMPTY_LIST);
  }

  @Test
  public void getById() throws Exception {
    Note note = noteService.getByTitle("First Note");
    Note byId = noteService.getById(note.getId());
    assertNotNull(byId);
  }

  @Test
  public void saveOrUpdate() throws Exception {
    Note note = new Note();
    note.setTitle("test");
    noteService.saveOrUpdate(note);
    Note test = noteService.getByTitle("test");
    assertNotNull(test);

  }

  @Test
  public void delete() throws Exception {
    Note test = noteService.getByTitle("First Note");
    noteService.delete(test.getId());
  }

  @Test
  public void deleteAll() throws Exception {
 /*   noteService.deleteAll();
    assertEquals(noteService.getAll(), Collections.EMPTY_LIST);*/
  }

}