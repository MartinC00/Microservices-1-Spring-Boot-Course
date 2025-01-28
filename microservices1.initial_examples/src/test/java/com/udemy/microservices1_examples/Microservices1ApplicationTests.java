package com.udemy.microservices1_examples;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Microservices1ApplicationTests {

	@Autowired
	MockMvc mock;

	@Order(0)
	@Test
	void testDeleteCurso() throws Exception{
		mock.perform(delete("/cursos?nombre=Spring Boot"));
	}

	@Order(1)
	@Test
	void testGetCursos() throws Exception {
		mock.perform(get("/cursos")).andDo(print());
	}

	@Order(2)
	@Test
	void testAddCurso() throws Exception{
		mock.perform(post("/cursos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nombre\": \"Angular 10\", \"duration\": 40, \"timeOfDay\": \"Evening\"}")
				).andDo(print());
	}
	@Order(3)
	@Test
	void testUpdateCurso() throws Exception{
		mock.perform(put("/cursos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nombre\": \"Angular 10\", \"duracion\": 20, \"horario\": \"Mañana\"}")
				).andDo(print());
	}
}
