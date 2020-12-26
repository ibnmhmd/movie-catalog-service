package org.robust.moviecatalogmicroservice.services;

import org.robust.moviecatalogmicroservice.models.CatalogItem;
import org.robust.moviecatalogmicroservice.models.Movie;
import org.robust.moviecatalogmicroservice.models.MovieRating;
import org.robust.moviecatalogmicroservice.models.RatingInfo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieDetailsService {

	 @Value("${app.movie.info.uri}")
	 private String MOVIE_INFO_URL;
	 
	@Autowired
	private WebClient.Builder webclientBuilder;
	/**
	 * @param rating
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getMovieDetailsByRatingIdFallback")
	public List<CatalogItem> getMovieDetailsByRatingId(MovieRating rating) {
		 List<CatalogItem> result = rating.getRatingInfo().stream().map((Function<? super RatingInfo, ? extends CatalogItem>) mapper -> {
			Movie movie = webclientBuilder.build().get()
					.uri(MOVIE_INFO_URL+mapper.getMovieId())
					.accept(org.springframework.http.MediaType.APPLICATION_JSON)
					.retrieve()  .bodyToMono(Movie.class).block();
			return new CatalogItem(movie , mapper.getRating());
		}).collect(Collectors.toList());
		 return result;
	}
	
	@SuppressWarnings("unused")
	private List<CatalogItem> getMovieDetailsByRatingIdFallback(MovieRating rating){
		return Arrays.asList(new CatalogItem(new Movie(0, "Dummy movie", "Circuit breaker", "Dummy Actore", "Circuit breaker") , 0 ));
	}
}
