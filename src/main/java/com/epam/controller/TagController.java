package com.epam.controller;

import com.epam.model.SessionData;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.services.impl.TagService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class TagController {

  private final SessionData sessionData;
  private final TagService tagService;

  @Autowired
  public TagController(SessionData sessionData, TagService tagService) {
    this.sessionData = sessionData;
    this.tagService = tagService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/tags")
  public List<Tag> getAllTagsByUser() {
    System.err.println(sessionData.getUser());

    return tagService.getByTagsByUser(sessionData.getUser());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/tags/{id}")
  public Tag getById(@PathVariable("id") Long id) {
    System.err.println(sessionData.getUser());

    return tagService.getById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/tags")
  public void createTag(@RequestBody Tag tag) {
    tag.setUser(sessionData.getUser());
    System.err.println(sessionData.getUser());
    tagService.saveOrUpdate(tag);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/tags/{id}")
  public void removeTag(@PathVariable("id") Long id) {
    System.err.println(sessionData.getUser());

    tagService.delete(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/tags/{id}")
  public void updateTag(@PathVariable Long id, @RequestBody Tag tag) {
    System.err.println(sessionData.getUser());

    tag.setId(id);
    tag.setUser(sessionData.getUser());
    tagService.saveOrUpdate(tag);
  }


}

