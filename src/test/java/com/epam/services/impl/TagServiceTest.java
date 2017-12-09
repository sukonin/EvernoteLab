package com.epam.services.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.epam.config.ApplicationConfiguration;
import com.epam.model.Tag;
import java.util.Collections;
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
public class TagServiceTest {

  @Autowired
  TagService tagService;

  @Test
  public void getAll() throws Exception {
    assertNotEquals(tagService.getAll(), Collections.EMPTY_LIST);
  }

  @Test
  public void getById() throws Exception {
    Tag tag = tagService.getById(1L);
    assertNotNull(tag);
  }

  @Test
  public void saveOrUpdate() throws Exception {
    Tag tag = new Tag();
    tag.setTag("test");
    tagService.saveOrUpdate(tag);
    assertNotNull(tag.getId());
  }

  @Test
  public void delete() throws Exception {
    Tag test = tagService.getByTag("work");
    tagService.delete(test.getId());
  }

  @Test
  public void deleteAll() throws Exception {
/*    tagService.deleteAll();
    assertEquals(tagService.getAll(), Collections.EMPTY_LIST);*/
  }

}