package org.robust.movieratingmicroservice.movieratingcontroller;

import java.util.LinkedList;
import java.util.List;

import org.robust.movieratingmicroservice.models.MovieRating;
import org.robust.movieratingmicroservice.models.RatingInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="movierating/api")
public class MovieRatingController {

    
	List<MovieRating> ratings = new LinkedList<>();
	List<RatingInfo> ratingInfo = new LinkedList<RatingInfo>();
	{
		init();
	}
	public void init() {
		for(int i = 0; i < 10; i++) {
			ratingInfo = new LinkedList<RatingInfo>();
			ratingInfo.add(new RatingInfo( 10+i , i+1));
			if(i < 6) {
				ratingInfo.add(new RatingInfo( 10+i+1 , i+3));
				ratingInfo.add(new RatingInfo( 10+i+2 , i+2));
				ratingInfo.add(new RatingInfo( 10+i+3 , i+4));
			}else {
				ratingInfo.add(new RatingInfo( 10+i-3 , i+1-5));
			}
			ratings.add(new MovieRating(100+i ,ratingInfo));
		}
	}
	@GetMapping(value = {"getRatings"})
	public List<MovieRating> getRatingsInfo() {
		System.out.println("#### invoking movie rating method from : MovieRatingController class ###");
		System.out.println(ratings);
		return ratings;
	}
	@GetMapping(value = {"{userId}"})
	public MovieRating getRatingByUserId(@PathVariable("userId") int userId) {
		MovieRating info = ratings.stream().filter((rating) -> rating.getUserId() == userId).findAny().orElse(null) ;
		return info;
	}

}
