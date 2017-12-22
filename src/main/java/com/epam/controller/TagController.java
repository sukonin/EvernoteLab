package com.epam.controller;


import com.epam.exception.NotFoundException;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.services.impl.TagService;
import com.epam.services.impl.UserService;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
@CrossOrigin(origins = "http://localhost:4200")
public class TagController {

  private final TagService tagService;
  private final UserService userService;

  @Autowired
  public TagController(TagService tagService, UserService userService) {
    this.tagService = tagService;
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/tags")
  public List<Tag> getAllTagsByUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByEmail(principal.toString());

    return tagService.getByTagsByUser(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/tags/{id}")
  public Tag getById(@PathVariable("id") Long id) {
    Tag tag = tagService.getById(id);
    if (tag == null) {
      throw new NotFoundException("Note with id:" + id + " not found!");
    }
    return tag;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/tags")
  public void createTag(@RequestBody Tag tag) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByEmail(principal.toString());

    tag.setUser(user);
    tagService.saveOrUpdate(tag);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/tags/{id}")
  public void removeTag(@PathVariable("id") Long id) {
    tagService.delete(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/tags/{id}")
  public void updateTag(@PathVariable Long id, @RequestBody Tag tag) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByEmail(principal.toString());

    tag.setId(id);
    tag.setUser(user);
    tagService.saveOrUpdate(tag);
  }


}

