package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.controller.DemoController;
import com.test.demo.entity.Demo;
import com.test.demo.service.DemoService;


@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
class DemoApplicationTests {
    @Autowired
    private MockMvc mvc;  
    
    @MockBean
    private DemoService service;
    
    @Test
    void getAllTest() throws Exception {
    	List<Demo> list = new ArrayList<Demo>() ;
    	Demo demo = new Demo();
		demo.setId(4);
		demo.setName("Test Name 4");
		demo.setAddress("Address 4");
		
		list.add(demo);
		
		Mockito.when(service.getAll()).thenReturn(list);
		
		String URI = "/demo/get/all";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(demo);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
    }
    
//    @Test
//    void addTest() throws Exception {		
//		Demo demo = new Demo();
//		demo.setId(4);
//		demo.setName("Test Name 4");
//		demo.setAddress("Address 4");
//		
//		String inputJson = this.mapToJson(demo);
//		
//		String URI = "demo/add";
//		
//		Mockito.when(service.add(Mockito.any(Demo.class))).thenReturn("success add");
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post(URI)
//				.accept(MediaType.APPLICATION_JSON).content(inputJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//		
//		String outputInJson = response.getContentAsString();
//		
//		assertThat(outputInJson).isEqualTo(inputJson);
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//    }
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void getAll() throws Exception {
////		RequestBuilder request = MockMvcRequestBuilders.get("get/all");
////		MvcResult result = mvc.perform(request).andReturn();
//		assertEquals(2, 2);
//	}
//
//	@Test
//	void testAdd() {
//		DemoController demoController = new DemoController();
//		Demo demo = new Demo();
//		demo.setId(4);
//		demo.setName("Test Name 4");
//		demo.setAddress("Address 4");
//
////		String inputJson = this.mapToJson(demo);
////		String uri = "/demo/get/all";
////		Mockito.when(service.getAll().thenReturn(demo));
////        String result = demoController.add(demo);
//	}
//	
//	@Test
//	void add(){
////		DemoController demoController = new DemoController();
////		Demo demo = new Demo();
////		demo.setId(4);
////		demo.setName("Test Name 4");
////		demo.setAddress("Address 4");
////
////        String result = demoController.add(demo);
//// 
////		assertEquals(result, "success add");
//	}
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
