package com.epam.controller;


import com.epam.config.ApplicationConfiguration;
import com.epam.model.Notebook;
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
public class NotebookControllerTest {


  @Autowired
  NotebookController notebookController;

  @Test
  public void test() {
    log.warning(notebookController.getAllNotebooks("1@epam.com").toString());

    log.warning(notebookController.getNotebookByTitle("title", "1@epam.com").toString());
  }

}
