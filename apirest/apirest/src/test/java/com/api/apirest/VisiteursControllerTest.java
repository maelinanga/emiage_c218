package com.api.apirest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class VisiteursControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Test
	public void testGetVisiteurs() throws Exception {
		mockMvc.perform(get("/visiteurs")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nom", is("Sophie")));
	}
	@Test
	public void testPostVisiteurs() throws Exception {
		mockMvc.perform(get("/creervisiteur")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
			      .andExpect(jsonPath("$.message").value("Hello World John!!!"));
	}
}