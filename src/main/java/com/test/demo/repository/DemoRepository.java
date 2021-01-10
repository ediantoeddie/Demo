package com.test.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.test.demo.entity.Demo;

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
	
	public PhotoList<Photo> search() throws FlickrException {
		Flickr flickr = new Flickr(apiKey, secretKey, new REST());
		System.out.println("search 1");
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.setBBox("-180", "-90", "180", "90");
		searchParameters.setMedia("photos");
		String [] tags = {"dog","cat"};
		searchParameters.setTags(tags);
		System.out.println("search 2");
		PhotoList<Photo> list = flickr.getPhotosInterface().search(searchParameters, 10, 0);
		System.out.println("search 3");

//		System.out.println("Image List");
//		for (int i = 0; i < list.size(); i++) {
//			Photo photo = list.get(i);
//			System.out.println("Image: " + i
//					+ "\nTitle: " + photo.getTitle()
//					+ "\nMedia: " + photo.getOriginalFormat()
//					+ "\nPublic: " + photo.isPublicFlag()
//					+ "\nTag: " + photo.getTags()
//					+ "\nUrl: " + photo.getUrl()
//					+ "\n");
//		}
////		System.out.println();
		
		return list;
	}
}
