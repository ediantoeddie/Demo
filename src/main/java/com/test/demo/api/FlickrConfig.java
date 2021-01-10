package com.test.demo.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.photos.Size;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

@Configuration
public class FlickrConfig {

	@Value("${flickrApiKey}")
	String apiKey;

	@Value("${flickrApiSecretKey}")
	String secretKey;

	@Bean
	public Flickr getFlickr() throws IOException, InterruptedException, ExecutionException, FlickrException {
		Flickr flickr = new Flickr(apiKey, secretKey, new REST());

		// Oauth
//		System.out.println("apiKey : "+apiKey);
//		System.out.println("secretKey : "+secretKey);
//		// Add oauthService
//		OAuth10aService service = new ServiceBuilder(apiKey)
//				.apiSecret(secretKey).build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
//
//		final Scanner in = new Scanner(System.in);
//
//		// Create Request Token
//		final OAuth1RequestToken reqToken = service.getRequestToken();
//
//		// Get Auth URL to confirm with flick
//		final String authUrl = service.getAuthorizationUrl(reqToken);
//		System.out.println(authUrl);
//		System.out.println("Paste the verifier code here : ");
//		System.out.print(">>");
//		final String oauthVerifier = in.nextLine();
//		System.out.println("reqToken.getToken()  : "+reqToken.getToken());
//		System.out.println("reqToken.getTokenSecret()  : "+reqToken.getTokenSecret());
//		System.out.println("reqToken.getRawResponse()  : "+reqToken.getRawResponse());
//
//		// trade the req token and verifier for access token 
//		final OAuth1AccessToken accessToken = service.getAccessToken(reqToken, oauthVerifier);
//
//		System.out.println("accessToken.getToken()  : "+accessToken.getToken());
//		System.out.println("accessToken.getTokenSecret()  : "+accessToken.getTokenSecret());
//		System.out.println("accessToken.getRawResponse()  : "+accessToken.getRawResponse());

		//			flickr.getAuthInterface().checkToken(reqToken);
		//			flickr.getAuthInterface().checkToken(accessToken.getToken(), accessToken.getTokenSecret());
		// end of Oauth

		SearchParameters searchParameters = new SearchParameters();
		searchParameters.setBBox("-180", "-90", "180", "90");
		searchParameters.setMedia("photos");
		String [] tags = {"dog","cat"};
		searchParameters.setTags(tags);
		PhotoList<Photo> list = flickr.getPhotosInterface().search(searchParameters, 10, 0);

		System.out.println("Image List");
		for (int i = 0; i < list.size(); i++) {
			Photo photo = list.get(i);
			System.out.println("Image: " + i
					+ "\nTitle: " + photo.getTitle()
					+ "\nMedia: " + photo.getOriginalFormat()
					+ "\nPublic: " + photo.isPublicFlag()
					+ "\nTag: " + photo.getTags()
					+ "\nUrl: " + photo.getUrl()
					+ "\n");
		}
		System.out.println();

		//	        PhotosInterface pi = new PhotosInterface(apiKey, secretKey, new REST());
		//	        System.out.println("pi: " + pi);
		//	        Photo currentPhoto = list.get(0);
		//	        System.out.println("currentPhoto url: " + currentPhoto.getUrl());
		//
		//	        // Get image using URL
		//	        BufferedImage bufferedImage = pi.getImage(currentPhoto.getUrl());
		//	        System.out.println("bi: " + bufferedImage);
		//
		//	        // Get image using Photo instance
		//	        bufferedImage = pi.getImage(currentPhoto, Size.SMALL);
		//
		//	        // Save image to file
		//	        System.out.println("bufferedImage: " + bufferedImage);
		//	        File outputfile = new File("image.jpg");
		//	        ImageIO.write(bufferedImage, "jpg", outputfile);
		//	        

		return flickr;

	}
}
