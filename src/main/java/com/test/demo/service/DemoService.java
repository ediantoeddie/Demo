package com.test.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.test.demo.entity.Demo;
import com.test.demo.entity.PhotoSearchResult;
import com.test.demo.repository.DemoRepository;

@Service
public class DemoService {

	@Autowired
	DemoRepository demoRepository;
	
	public List<Demo> getAll() {
		return demoRepository.getAll();
	}

	public String add(Demo demo) {
		return demoRepository.add(demo);
	}

	public String edit(Demo demo) {
		// TODO Auto-generated method stub
		return demoRepository.edit(demo);
	}

	public String delete(int id) {
		return demoRepository.delete(id);
	}

	public ResponseEntity<List<PhotoSearchResult>> getPhotos(String searchData) {
		// TODO Auto-generated method stub
		return demoRepository.getPhotos(searchData);
	}

}
