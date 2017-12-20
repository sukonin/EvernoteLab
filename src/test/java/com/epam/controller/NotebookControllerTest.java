package com.epam.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.WebContextTestExecutionListener;
import com.epam.config.web.WebApplication;
import com.epam.config.web.WebConfig;
import com.epam.model.Notebook;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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


@ContextConfiguration(classes = {WebApplication.class, WebConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@TestExecutionListeners({WebContextTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
public class NotebookControllerTest {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private MockMvc mvc;
  @Resource
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setupSpec() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(wac)
        .apply(springSecurity())
        .addFilter(springSecurityFilterChain)
        .build();
  }

  @Test
  public void getById() throws Exception {
    mvc.perform(get("/notebooks/1")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void getAllNotebookByUser() throws Exception {
    mvc.perform(get("/notebooks")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void createNotebook() throws Exception {

    Notebook notebook = new Notebook();
    notebook.setTitle("test");

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonObject = objectMapper.writeValueAsString(notebook);

    mvc.perform(post("/notebooks")
        .with(httpBasic("test", "test"))
        .content(jsonObject)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void deleteNotebookById() throws Exception {
    mvc.perform(delete("/notebooks/2")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  public void updateNotebook() throws Exception {
    String contentAsString =
        mvc.perform(get("/notebooks/1")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    ObjectMapper objectMapper = new ObjectMapper();
    Notebook notebook = objectMapper.readValue(contentAsString, Notebook.class);
    notebook.setTitle("another title");

    String json = objectMapper.writeValueAsString(notebook);

    mvc.perform(put("/notebooks/1")
        .with(httpBasic("test", "test"))
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }

}