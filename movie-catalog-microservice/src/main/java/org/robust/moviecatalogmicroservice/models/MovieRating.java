package org.robust.moviecatalogmicroservice.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Data
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class MovieRating {
	private int userId;
	private List<RatingInfo> ratingInfo;
}
