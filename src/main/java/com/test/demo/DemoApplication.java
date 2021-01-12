package com.test.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.test.demo.entity.Demo;
import com.test.demo.entity.PhotoSearchResult;
import com.test.demo.repository.DemoRepository;

@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

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
