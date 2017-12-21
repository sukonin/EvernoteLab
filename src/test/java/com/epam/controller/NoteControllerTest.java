package com.epam.controller;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.web.WebApplication;
import com.epam.config.web.WebConfig;
import com.epam.model.Note;
import com.epam.model.Notebook;
import com.epam.model.User;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import java.time.LocalDate;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = {ApplicationConfiguration.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class NoteControllerTest {

  @Autowired
  private NotebookService notebookService;
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;
  @Resource
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setupSpec() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(wac)
        .addFilter(springSecurityFilterChain)
        .build();
  }

  @Test
  public void getAllNotes() throws Exception {

    mvc.perform(get("/notes")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void getNoteById() throws Exception {
    mvc.perform(get("/notes/1")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteNode() throws Exception {
    mvc.perform(delete("/notes/1")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void createNoteInNotebook() throws Exception {
    Note note = new Note();
    note.setTitle("test");
    note.setIsActive(true);
    note.setDate(Date.valueOf(LocalDate.now()));

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(note);

    mvc.perform(post("/notes")
        .with(httpBasic("test", "test"))
        .param("notebook_id", "1")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void updateNote() throws Exception {
    String contentAsString = mvc.perform(get("/notes/1")
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    ObjectMapper objectMapper = new ObjectMapper();
    Note note = objectMapper.readValue(contentAsString, Note.class);
    note.setTitle("another title");

    String json = objectMapper.writeValueAsString(note);

    mvc.perform(put("/notes/1")
        .content(json)
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void getAllNotesByNotebook() throws Exception {
    Notebook byId = notebookService.getById(1L);

    ObjectMapper objectMapper = new ObjectMapper();

    String json = objectMapper.writeValueAsString(byId);

    mvc.perform(get("/notebooks/1/notes")
        .content(json)
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void putAndDeleteTagFromNote() throws Exception {
    mvc.perform(put("/notes/1/1")
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(delete("/notes/1/1")
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  public void getAllNotesByTag() throws Exception {
    /*add note to tag*/
    mvc.perform(put("/notes/1/1")
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mvc.perform(get("/notes/tag/priority")
        .with(httpBasic("test","test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}