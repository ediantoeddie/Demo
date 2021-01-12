package com.test.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
 
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.test.demo.entity.Demo;
import com.test.demo.entity.PhotoSearchResult;

@Repository
public class DemoRepository {
	public List<Demo> demo = new ArrayList<Demo>();
	
	@Value("${flickrApiKey}")
	String apiKey;

	@Value("${flickrApiSecretKey}")
	String secretKey;

	public List<Demo> getAll() {
		return demo;
	}

	public String add(Demo demo2) {
		demo.add(demo2);
		return "success add";
	}

	public String edit(Demo demo2) {
		demo.stream().filter(d -> d.getId() == demo2.getId()).forEach(d -> {
			d.setAddress(demo2.getAddress());
			d.setName(demo2.getName());
		});
		return "success update";
	}

	public String delete(int id) {
		demo.remove(id-1);
		return "Deleted";				
	}
	
	public ResponseEntity<List<PhotoSearchResult>> getPhotos(String searchData) {
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.setText(searchData);
		String apiKey = "f045b28fca18e8c6c82eb9832f007238";
		String sharedSecret = "6f97aff6a5034de1";
		Flickr f = new Flickr(apiKey, sharedSecret, new REST());
		List<PhotoSearchResult> psr = new ArrayList<PhotoSearchResult>();
		PhotoList<Photo> list = null;
		try {
			list = f.getPhotosInterface().search(searchParameters, 10, 1);
		} catch (FlickrException e) {
			return new ResponseEntity<List<PhotoSearchResult>>(HttpStatus.BAD_REQUEST);
		}
		
		if (CollectionUtils.isEmpty(list)) {
			return new ResponseEntity<List<PhotoSearchResult>>(HttpStatus.NO_CONTENT);
		}
		
		for (Photo photo : list) {
			PhotoSearchResult p = new PhotoSearchResult();
			p.setDescription(photo.getDescription());
			p.setTitle(photo.getTitle());
			p.setSmallUrl(photo.getSmallUrl());
			p.setLargeUrl(photo.getLargeUrl());
			p.setUrl(photo.getUrl());
			psr.add(p);
		}
			
		return new ResponseEntity<List<PhotoSearchResult>>(psr, HttpStatus.OK);
	}
}
