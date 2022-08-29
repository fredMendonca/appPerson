package com.api.control.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.api.control.dto.PersonDto;
import com.api.control.services.PersonService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@WebMvcTest
public class PersonControllerTest {
	
	@Autowired
	private PersonController personController;
	@MockBean
	private PersonService personService;
	@MockBean
	private PersonDto personDto;
	@BeforeEach
	public void setup() {
		standaloneSetup(this.personController);
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPerson() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/persons")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarFalha_QuandoBuscarPerson() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/persons/teste")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
}
