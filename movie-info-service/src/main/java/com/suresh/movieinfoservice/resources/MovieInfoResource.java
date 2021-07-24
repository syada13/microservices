package com.suresh.movieinfoservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.suresh.movieinfoservice.models.Movie;
import com.suresh.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
	
	/*@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId){
		 return  new Movie(movieId,"BlackHowakDown","One of the best Holiwood movie");
		 
	}*/
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
	}

}
