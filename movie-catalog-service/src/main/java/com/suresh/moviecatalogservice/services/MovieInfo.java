package com.suresh.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.suresh.moviecatalogservice.models.CatalogItem;
import com.suresh.moviecatalogservice.models.Movie;
import com.suresh.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	RestTemplate restTemplate;
	
	// Implement BulkHead pattern using Hystrix Command by creating separate thread pool
	
	@HystrixCommand(
			fallbackMethod="getFallbackCatalogItem",
			threadPoolKey = "movieInfoPool",
			commandProperties = {
					@HystrixProperty(name = "coreSize", value = "40"),
					@HystrixProperty(name = "maxQueueSize", value = "15"),
					
			 }
			)
	public CatalogItem getCatalogItem(Rating rating) {
		
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" +rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getMovieId(),movie.getDescription(),Integer.valueOf(rating.getMovieId()));
		
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", Integer.valueOf(rating.getRating()));
		
	}

}
