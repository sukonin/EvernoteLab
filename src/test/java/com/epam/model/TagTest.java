package com.epam.model;

import static org.junit.Assert.assertEquals;

import com.epam.config.ApplicationConfiguration;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
public class TagTest {

  @Test
  public void hashCodeTest() {
    Tag tag = new Tag();
    Tag tag1 = new Tag();

    assertEquals(tag.hashCode(), tag1.hashCode());
  }


}
