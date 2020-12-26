package org.robust.movieratingmicroservice.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

@Data
@CommonsLog
@AllArgsConstructor
public class MovieRating {
	private int userId;
	private List<RatingInfo> ratingInfo;
}
