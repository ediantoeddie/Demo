package com.test.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.demo.entity.Demo;
import com.test.demo.repository.DemoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Value("${greet}")
	private String greet;
	
	@Autowired
	DemoRepository demoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(greet);
		Demo d1 = new Demo(1,"Test Name", "Test Address");
		Demo d2 = new Demo(2,"Test Name2", "Test Address2");
		
		demoRepository.demo.addAll(Arrays.asList(d1,d2));
	}

}
