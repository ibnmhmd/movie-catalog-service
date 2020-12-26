package org.robust.movieinfomicroservice.movieInfoController;

import java.util.LinkedList;
import java.util.List;

import org.robust.movieinfomicroservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movieinfo/api")
public class MovieInfoController {
	List<Movie> movies = new LinkedList<>();
	{
		init();
	}
	public void init() {
		for(int i = 0; i < 10; i++) {
			movies.add(new Movie(10+i , "Name-"+i , "Desc-"+i , "Actor-"+i , "Producor-"+i));
		}
	}
	@GetMapping(value = {"getMovies"})
	public List<Movie> getRatingsInfo() {
		return movies;
	}
	@GetMapping(value = {"{movieId}"})
	public Movie getMovieInfoByMovieId(@PathVariable("movieId") int movieId) {
		System.out.println("Getting in getMovieInfoByMovieId info for : "+ movieId);
		Movie info = movies.stream().filter((movie) -> movie.getId() == movieId).findAny().orElse(new Movie(0, "", "", "", "")) ;
		return info;
	}
}
