package com.epam.init;

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
import javax.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class DBInitializer {


  @Autowired
  NotebookService notebookService;
  @Autowired
  UserService userService;
  @Autowired
  NoteService noteService;
  @Autowired
  TagService tagService;


 /*
      H2Console http://localhost:8082/
      url = jdbc:h2:mem:test
      user = sa
      password =
*/

  @PostConstruct
  public void initDB() {
    log.info("Staring database initialization...");
    /*Init User Table*/
    User user = new User();
    user.setEmail("user1@mail.ru");
    user.setPassword("password");
    user.setUsername("username");
    userService.saveOrUpdate(user);

    User user2 = new User();
    user2.setEmail("user2@mail.ru");
    user2.setPassword("password");
    user2.setUsername("username");
    userService.saveOrUpdate(user2);

    /*Init Notebook Table*/
    Notebook notebook = new Notebook();
    notebook.setTittle("First Notebook for first User");
    notebook.setUser(user);
    notebookService.saveOrUpdate(notebook);

    Notebook notebook1 = new Notebook();
    notebook1.setTittle("Second Notebook for first User");
    notebook1.setUser(user);
    notebookService.saveOrUpdate(notebook1);

    Notebook notebook2 = new Notebook();
    notebook2.setTittle("Notebook for Second User");
    notebook2.setUser(user2);
    notebookService.saveOrUpdate(notebook2);


    /*Init Note Table*/
    Note note = new Note();
    note.setTitle("First Note");
    note.setIsActive(true);
    LocalDate of = LocalDate.of(2017, 12, 29);
    note.setDate(Date.valueOf(of));
    note.setContent("TODO Application! And go to PROD!");
    note.setNotebook(notebook);

    noteService.saveOrUpdate(note);

    Note note2 = new Note();
    note2.setContent("blablaContent");
    note2.setIsActive(false);
    note2.setTitle("BlaBla");
    note2.setDate(Date.valueOf(LocalDate.now()));
    note2.setNotebook(notebook1);

    noteService.saveOrUpdate(note2);


    Note note3 = new Note();
    note3.setNotebook(notebook2);
    note3.setContent("third");
    note2.setIsActive(false);
    note2.setTitle("Third");
    noteService.saveOrUpdate(note3);

    /*Init Tag Table*/

    Tag tag = new Tag();
    tag.setTag("work");
    tagService.saveOrUpdate(tag);

    Tag tag2 = new Tag();
    tag2.setTag("home");
    tagService.saveOrUpdate(tag2);

    Tag tag3 = new Tag();
    tag3.setTag("vacation");
    tagService.saveOrUpdate(tag3);

    Tag tag4 = new Tag();
    tag4.setTag("priority");
    tagService.saveOrUpdate(tag4);


    noteService.addTagToNote(tag, note);
    noteService.addTagToNote(tag4,note);
    noteService.addTagToNote(tag, note2);

    log.info("Database initialization finished!");

    /*while (true) {
    }*/

  }

}
