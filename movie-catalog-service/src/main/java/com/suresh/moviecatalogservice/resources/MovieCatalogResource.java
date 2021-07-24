
package com.suresh.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.suresh.moviecatalogservice.models.CatalogItem;
import com.suresh.moviecatalogservice.models.Movie;
import com.suresh.moviecatalogservice.models.Rating;
import com.suresh.moviecatalogservice.models.UserRating;
import com.suresh.moviecatalogservice.services.MovieInfo;
import com.suresh.moviecatalogservice.services.UserRatingInfo;



@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	
	
	@RequestMapping("{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId){
				
		/*
		1. Get all  rated movie ID's		 
		2. For each movie id, call movie info service and get details
		3. put them all together
		 * */
		UserRating userRating = userRatingInfo.getUserRating(userId);
		return userRating.getRatings().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());		 								
	}
	
	/*Rest asynchronous call using WebClient		
	return ratings.stream().map(rating -> {
		Movie movie = webClientBuilder.build()
		.get()
		.uri("http://localhost:8082/movies/" +rating.getMovieId())
		.retrieve()
		.bodyToMono(Movie.class)
		.block();
		
		return new CatalogItem(movie.getName(),"Test",Integer.valueOf(rating.getRating()));
		
	}).collect(Collectors.toList());*/
	
}



