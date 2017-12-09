package com.epam.services.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
  public void delete() throws Exception {
    Notebook notebook = notebookService.getByTitle("First Notebook for first User");
    notebookService.delete(notebook.getId());
    Notebook deleted = notebookService.getByTitle("First Notebook for first User");
//    assertNull(deleted);
  }

  @Test
  public void deleteAll() throws Exception {
/*    notebookService.deleteAll();
    assertEquals(notebookService.getAll(), Collections.EMPTY_LIST);*/
  }

}