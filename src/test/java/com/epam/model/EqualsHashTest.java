package com.epam.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import com.epam.config.ApplicationConfiguration;
import com.epam.services.impl.NoteService;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.TagService;
import com.epam.services.impl.UserService;
import java.sql.Date;
import java.time.LocalDate;
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
public class EqualsHashTest {

  @Autowired
  NotebookService notebookService;
  @Autowired
  UserService userService;
  @Autowired
  NoteService noteService;
  @Autowired
  TagService tagService;

  @Test
  public void test2() {

    Tag tag = new Tag();
    tag.setTag("first");
    Tag tag1 = new Tag();
    tag1.setTag("second");
    tagService.saveOrUpdate(tag1);
    tagService.saveOrUpdate(tag);

    assertNotEquals(tag.hashCode(), tag1.hashCode());
    assertFalse(tag.equals(tag1));

    User user1 = new User();
    user1.setEmail("first");
    User user2 = new User();
    user2.setEmail("second");
    userService.saveOrUpdate(user1);
    userService.saveOrUpdate(user2);

    assertNotEquals(user1.hashCode(), user2.hashCode());
    assertFalse(user1.equals(user2));

    Notebook notebook = new Notebook();
    notebook.setTittle("first");
    notebook.setUser(user1);
    notebookService.saveOrUpdate(notebook);

    Notebook notebook1 = new Notebook();
    notebook1.setTittle("second");
    notebook1.setUser(user2);
    notebookService.saveOrUpdate(notebook1);

    assertNotEquals(notebook.hashCode(), notebook1.hashCode());
    assertFalse(notebook.equals(notebook1));

    Note note = new Note();
    note.setTitle("first");
    note.setNotebook(notebook);
    note.setContent("content");
    note.setIsActive(true);
    note.setDate(Date.valueOf(LocalDate.now()));
    noteService.saveOrUpdate(note);

    Note note1 = new Note();
    note1.setTitle("second");
    note1.setNotebook(notebook1);
    note1.setIsActive(false);
    note1.setContent("cont");
    note1.setDate(Date.valueOf(LocalDate.now()));
    noteService.saveOrUpdate(note1);

    assertNotEquals(note1.hashCode(), note.hashCode());
    assertFalse(note.equals(note1));

  }


}
