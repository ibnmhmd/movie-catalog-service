package org.robust.movieinfomicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
 private int id;
 private String name;
 private String description;
 private String actor;
 private String producor;
}
