package org.robust.moviecatalogmicroservice.services;

import org.robust.moviecatalogmicroservice.models.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RatingsService {
	@Autowired
	private WebClient.Builder webclientBuilder;
	
	@Value("${app.movie.rating.uri}")
	private  String RATING_URL;
	
	/**
	 * @param userId
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getRatingByUserIdFallback")
	public MovieRating getRatingDetailsByUserId(int userId) {
		return webclientBuilder.build()
				             .get().uri(RATING_URL+userId).accept(org.springframework.http.MediaType.APPLICATION_JSON)
				             .retrieve().bodyToFlux(MovieRating.class).blockFirst();
	}
	@SuppressWarnings("unused")
	private MovieRating getRatingByUserIdFallback(int userId){
		return new MovieRating();
	}
}
