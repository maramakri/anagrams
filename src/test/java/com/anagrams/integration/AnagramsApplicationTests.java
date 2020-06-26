package com.anagrams.integration;

import com.anagrams.controller.AnagramsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class AnagramsApplicationTests {

	@Autowired
	private AnagramsController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void areAnagramsShouldReturnStatusOk() throws Exception {
		this.mockMvc.perform(get("/anagrams/claw/walc")).andDo(print()).andExpect(status().isOk())
				.andExpect(ResultMatcher.matchAll());
	}

	@Test
	public void areAnagramsShouldReturnStatusBadRequest() throws Exception {
		this.mockMvc.perform(get("/anagrams/claw/--")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void areAnagramsShouldReturnTrue() throws Exception {
		this.mockMvc.perform(get("/anagrams/claw/walc"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().matches("areAnagrams:true"));
	}

	@Test
	public void areAnagramsShouldReturnFalse() throws Exception {
		this.mockMvc.perform(get("/anagrams/claw/ramk"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().matches("areAnagrams:false"));
	}

	@Test
	public void getAnagramsShouldReturnValues() throws Exception {
		this.mockMvc.perform(get("/anagrams/claw"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().matches("awlc"));
	}

	@Test
	public void getAnagramsShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/anagrams/--"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

}
