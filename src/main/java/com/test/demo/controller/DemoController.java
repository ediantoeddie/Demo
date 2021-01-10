package com.test.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.test.demo.entity.Demo;
import com.test.demo.service.DemoService;
import com.test.demo.entity.PhotoSearchResult;

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
	
	@GetMapping("/search")
	public PhotoList<Photo> search() throws FlickrException{
		return demoService.search();
	}
	
//	@RequestMapping(value = "/getPhotos/{search}", method = RequestMethod.GET, headers = "Accept=application/json")
//	public ResponseEntity<List<PhotoSearchResult>> getPhotos(@PathVariable String search, HttpSession session) {
//		SearchParameters searchParameters = new SearchParameters();
//		searchParameters.setText(search);
//		String apiKey = "f045b28fca18e8c6c82eb9832f007238";
//		String sharedSecret = "6f97aff6a5034de1";
//		Flickr f = new Flickr(apiKey, sharedSecret, new REST());
//		List<PhotoSearchResult> psr = new ArrayList<PhotoSearchResult>();
//		PhotoList<Photo> list = null;
//		try {
//			list = f.getPhotosInterface().search(searchParameters, 10, 1);
//		} catch (FlickrException e) {
//			return new ResponseEntity<List<PhotoSearchResult>>(HttpStatus.BAD_REQUEST);
//		}
//		
//		if (CollectionUtils.isEmpty(list)) {
//			return new ResponseEntity<List<PhotoSearchResult>>(HttpStatus.NO_CONTENT);
//		}
//		
//		for (Photo photo : list) {
//			PhotoSearchResult p = new PhotoSearchResult();
//			p.setDescription(photo.getDescription());
//			p.setTitle(photo.getTitle());
//			p.setSmallUrl(photo.getSmallUrl());
//			p.setLargeUrl(photo.getLargeUrl());
//			psr.add(p);
//		}
//		
////		saveSearchHistoryList(session, search);
//		
//		return new ResponseEntity<List<PhotoSearchResult>>(psr, HttpStatus.OK);
//	}
}
