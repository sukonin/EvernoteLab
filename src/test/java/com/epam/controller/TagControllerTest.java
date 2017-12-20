package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import com.epam.config.ApplicationConfiguration;
import com.epam.config.web.WebApplication;
import com.epam.config.web.WebConfig;
import com.epam.model.Tag;
import com.epam.model.User;
import com.epam.services.impl.NotebookService;
import com.epam.services.impl.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TagControllerTest {

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
  public void getById() throws Exception {
    mvc.perform(get("/tags/1")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status()
            .isOk());
  }

  @Test
  public void createTag() throws Exception {
    Tag tag = new Tag();
    tag.setTag("test");

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonObject = objectMapper.writeValueAsString(tag);

    mvc.perform(post("/tags")
        .content(jsonObject)
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void removeTag() throws Exception {
    mvc.perform(delete("/tags/2")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  public void getAllTagsByUser() throws Exception {
    mvc.perform(get("/tags")
        .with(httpBasic("test", "test"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }


  @Test
  public void updateTag() throws Exception {
    String contentAsString = mvc.perform(get("/tags/1")
        .contentType(MediaType.APPLICATION_JSON)
        .with(httpBasic("test", "test")))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    ObjectMapper objectMapper = new ObjectMapper();
    Tag tag = objectMapper.readValue(contentAsString, Tag.class);
    tag.setTag("another tag");
    String json = objectMapper.writeValueAsString(tag);

    mvc.perform(put("/tags/1")
        .with(httpBasic("test", "test"))
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }


}