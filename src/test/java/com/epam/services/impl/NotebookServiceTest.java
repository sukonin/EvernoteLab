package com.epam.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.Notebook;
import com.epam.model.User;
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
public class NotebookServiceTest {

  @Autowired
  NotebookService notebookService;
  @Autowired
  UserService userService;
  @Autowired
  NoteService noteService;

  @Test
  public void getByUser() throws Exception {
    User user = userService.getByEmail("user1@mail.ru");
    List<Notebook> allNotebookByUser = notebookService.getAllNotebookByUser(user);
    assertNotEquals(allNotebookByUser, Collections.EMPTY_LIST);
  }

  @Test
  public void getByTitle() throws Exception {
    Notebook notebook = notebookService.getByTitle("First Notebook for first User");
    log.info(notebook.toString());
    assertNotNull(notebook);
  }

  @Test
  public void getAll() throws Exception {
    assertNotEquals(notebookService.getAll(), Collections.EMPTY_LIST);
  }

  @Test
  public void getById() throws Exception {
    Notebook notebook = notebookService.getByTitle("First Notebook for first User");
    Notebook byId = notebookService.getById(notebook.getId());
    assertNotNull(byId);
  }

  @Test
  public void zdelete() throws Exception {
    Notebook notebook = notebookService.getByTitle("Notebook for Second User");
    notebookService.delete(notebook.getId());
    Notebook deleted = notebookService.getByTitle("Notebook for Second User");
    assertNull(deleted);
  }

  @Test
  public void zdeleteAll() throws Exception {
    notebookService.deleteAll();
    assertEquals(notebookService.getAll(), Collections.EMPTY_LIST);
  }

}