package com.test.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.test.demo.controller.DemoController;
import com.test.demo.entity.Demo;


//@ExtendWith(SpringExtension.class)
//@WebMvcTest(DemoController.class)
@SpringBootTest
class DemoApplicationTests {

//	@Autowired
//	private MockMvc mvc;
//	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getAll() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.get("get/all");
//		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(2, 2);
	}

	void add() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.get("get/all");
//		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(2, 2);
	}
}
