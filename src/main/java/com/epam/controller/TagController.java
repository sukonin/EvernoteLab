package com.epam.controller;

import com.epam.model.SessionData;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.services.impl.NoteService;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.TagService;
import com.epam.services.impl.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

  private final NotebookService notebookService;
  private final NoteService noteService;
  private final UserService userService;
  private final SessionData sessionData;
  private final TagService tagService;


  @Autowired
  public TagController(NotebookService notebookService,
      NoteService noteService, UserService userService,
      SessionData sessionData, TagService tagService) {
    this.notebookService = notebookService;
    this.noteService = noteService;
    this.userService = userService;
    this.sessionData = sessionData;
    this.tagService = tagService;
  }


  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/tags")
  public void createTag(@RequestBody @Valid Tag tag) {
    User user = sessionData.getUser();
    tag.setUser(user);
    tagService.saveOrUpdate(tag);
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/tags/{tag}")
  public void removeTag(@PathVariable("tag") String tag) {

    User user = sessionData.getUser();
    Tag byTag = tagService.getByTag(tag, user);
    tagService.delete(byTag.getId());

  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/tags")
  public List<Tag> getAllTagsByUser() {
    User user = sessionData.getUser();
    return tagService.getByTagsByUser(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/tags/{title}")
  public Tag getTagByTitleAndUser(@PathVariable("title") String tag) {
    User user = sessionData.getUser();
    Tag byTag = tagService.getByTag(tag, user);
    if(byTag==null){
      throw new RuntimeException("Bla!");
    }
    return byTag;
  }


}

