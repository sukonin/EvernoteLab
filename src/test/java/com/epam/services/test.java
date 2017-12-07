package com.epam.services;


import com.epam.config.ApplicationConfiguration;
import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.services.impl.NoteService;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.TagService;
import com.epam.services.impl.UserService;
import java.sql.Date;
import java.time.LocalDate;
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
public class test {

  @Autowired
  NotebookService notebookService;
  @Autowired
  UserService userService;
  @Autowired
  NoteService noteService;
  @Autowired
  TagService tagService;

  @Transactional
  public void initDB() {

    /*Init User Table*/
    User user = new User();
    user.setEmail("user1@mail.ru");
    user.setPassword("password");
    user.setUsername("username");

    User user2 = new User();
    user2.setEmail("user2@mail.ru");
    user2.setPassword("password");
    user2.setUsername("username");

    userService.saveOrUpdate(user);
    userService.saveOrUpdate(user2);

    /*Init Notebook Table*/
    Notebook notebook = new Notebook();
    notebook.setTittle("First Notebook for first User");
    notebook.setUser(user);

    Notebook notebook1 = new Notebook();
    notebook1.setTittle("Second Notebook for first User");
    notebook1.setUser(user);
    Notebook notebook2 = new Notebook();
    notebook2.setTittle("Notebook for Second User");
    notebook2.setUser(user2);
    notebookService.saveOrUpdate(notebook);
    notebookService.saveOrUpdate(notebook1);
    notebookService.saveOrUpdate(notebook2);


     /*Init Tag Table*/
    Tag tag1 = new Tag();
    tag1.setTag("work tag");
    Tag tag4 = new Tag();
    tag4.setTag("High Priority");
    Tag tag2 = new Tag();
    tag2.setTag("home tag");
    Tag tag3 = new Tag();
    tag3.setTag("Vacation tag");

    tagService.saveOrUpdate(tag1);
    tagService.saveOrUpdate(tag2);
    tagService.saveOrUpdate(tag3);
    tagService.saveOrUpdate(tag4);

  /*Init Note Table*/
    Note note = new Note();
    note.setTitle("First Note");
    note.setActive(true);
    LocalDate of = LocalDate.of(2017, 12, 29);
    note.setDate(Date.valueOf(of));
    note.setContent("TODO Application! And go to PROD!");
    note.setNotebook(notebook);


    Note note2 = new Note();
    note2.setNotebook(notebook1);
    note2.setContent("blablaContent");
    note2.setActive(false);
    note2.setTitle("BlaBla");
    note2.setDate(Date.valueOf(LocalDate.now()));


    noteService.saveOrUpdate(note);
    noteService.saveOrUpdate(note2);

/*
    log.warning(noteService.getAllNotesByTag(tag1).toString());
*/








  }


  @Test
  public void main() {
    initDB();

    while (true) {
      /*H2Console http://localhost:8082/
      * url = jdbc:h2:mem:test
      * user = sa
      * password =
      * */

    }
  }


}
