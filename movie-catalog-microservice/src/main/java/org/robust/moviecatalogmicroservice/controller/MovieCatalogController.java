package org.robust.moviecatalogmicroservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.robust.moviecatalogmicroservice.models.CatalogItem;
import org.robust.moviecatalogmicroservice.models.MovieRating;
import org.robust.moviecatalogmicroservice.services.MovieDetailsService;
import org.robust.moviecatalogmicroservice.services.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController(value = "catalog")
@RequestMapping(value = {"catalog/api"} )
@Log
@FieldDefaults(makeFinal=false, level=AccessLevel.PRIVATE)
public class MovieCatalogController {

	@Value("${app.movie.rating.uri}")
	private  String RATING_URL;
	
	@Autowired
	MovieDetailsService movieDetailsService;
	
	@Autowired
	RatingsService ratingsService;
	
	@Autowired
	private WebClient.Builder webclientBuilder;

	@HystrixCommand(fallbackMethod = "getRatingsInfoFallback")
	@GetMapping(value = {"getRatings"})
	public Flux<MovieRating> getRatingsInfo() {
		log.log(Level.FINE, "Getting ratings");
		Flux<MovieRating> ratings = null;
		try {
			
			ratings = webclientBuilder.build()
					  .get(). uri(RATING_URL+"getRatings")
					  .accept(org.springframework.http.MediaType.APPLICATION_JSON)
					  .retrieve().bodyToFlux(MovieRating.class);	
		}catch(Exception e) {
			log.log(Level.SEVERE, "Error while getting ratings :: "+ e.getMessage());
		}
		return ratings;
	}
	
	@GetMapping(value = {"{userId}"})
	public List<CatalogItem> getRatingByUserId(@PathVariable("userId") int userId) {
		 List<CatalogItem> items = null;
		try {
			MovieRating rating = ratingsService.getRatingDetailsByUserId(userId);
			items = movieDetailsService.getMovieDetailsByRatingId(rating);
		}catch(Exception e) {
			System.out.println("Error executing getRatingByUserId :: "+ e);			
		}
		return items;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private Flux<MovieRating> getRatingsInfoFallback(){
		Flux<MovieRating> ratings =  (Flux<MovieRating>)Arrays.asList(new MovieRating(0 , Arrays.asList(null)));
		return ratings;
	}

}
