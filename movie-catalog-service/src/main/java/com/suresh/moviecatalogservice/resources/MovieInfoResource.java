package com.suresh.moviecatalogservice.resources;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.moviecatalogservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
	
	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId){
		 return  new Movie(movieId,"3 Idiots","4");
		 
	}

}
