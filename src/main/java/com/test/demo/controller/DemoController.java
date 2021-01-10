package com.test.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.entity.Demo;
import com.test.demo.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
//	@RequestMapping(value="/")
//	public String Greet() {
//		return "Hello and Try Again";
//	}
	@Autowired
	DemoService demoService;
	
	@GetMapping("/get/all")
	public List<Demo> getAll(){
		return demoService.getAll();
	}
	
	@PostMapping("/add")
	public String add(@RequestBody Demo demo){
		return demoService.add(demo);
	}
	
	@PutMapping("/edit")
	public String edit(@RequestBody Demo demo){
		return demoService.edit(demo);
	}
	
	@DeleteMapping("/delete")
	public String edit(@RequestParam int id){
		return demoService.delete(id);
	}
}
