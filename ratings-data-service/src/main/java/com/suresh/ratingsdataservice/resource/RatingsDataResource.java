package com.suresh.ratingsdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.ratingsdataservice.model.Rating;
import com.suresh.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		
		return new Rating(movieId,"4");
		
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userid) {
		UserRating userRating = new UserRating();
		userRating.initData(userid);
		return userRating;
		
	}

}
