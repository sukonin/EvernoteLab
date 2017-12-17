package com.epam.controller;




import static com.jayway.restassured.RestAssured.DEFAULT_PATH;
import static com.jayway.restassured.RestAssured.DEFAULT_PORT;
import static com.jayway.restassured.RestAssured.DEFAULT_URI;
import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import com.epam.config.AppInit;
import com.epam.config.ApplicationConfiguration;

import com.epam.config.SwaggerConfig;
import com.epam.config.WebConfig;
import com.jayway.restassured.RestAssured;
import lombok.extern.java.Log;
import org.h2.server.web.WebApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Log
public class NotebookControllerTest {

  @Before
  public void setUp() throws Exception {
    RestAssured.baseURI = DEFAULT_URI;
    RestAssured.port = DEFAULT_PORT;
    RestAssured.basePath = DEFAULT_PATH;
  }

  @Test
  public void test() {


   /* given().when()
        .get("/notebooks/")
        .then()
        .statusCode(SC_OK)
        .body("$", not(empty()));*/

  }

}
