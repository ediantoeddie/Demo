package com.test.demo.api;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
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
	public Flickr getFlickr() {
		Flickr flickr = new Flickr(apiKey, secretKey, new REST());
		try {			
			System.out.println("apiKey : "+apiKey);
			System.out.println("secretKey : "+secretKey);
			// Add oauthService
			OAuth10aService service = new ServiceBuilder(apiKey)
					.apiSecret(secretKey).build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
			
			final Scanner in = new Scanner(System.in);
			
			// Create Request Token
			final OAuth1RequestToken reqToken = service.getRequestToken();
			
			// Get Auth URL to confirm with flick
			final String authUrl = service.getAuthorizationUrl(reqToken);
			System.out.println(authUrl);
			System.out.println("Paste the verifier code here : ");
			System.out.print(">>");
			final String oauthVerifier = in.nextLine();
			
			// trade the req token and verifier for access token 
			final OAuth1AccessToken accessToken = service.getAccessToken(reqToken, oauthVerifier);
			
			System.out.println("accessToken.getToken()  : "+accessToken.getToken());
			System.out.println("accessToken.getTokenSecret()  : "+accessToken.getTokenSecret());
			System.out.println("accessToken.getRawResponse()  : "+accessToken.getRawResponse());
			
			flickr.getAuthInterface().checkToken(accessToken);
			return flickr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
