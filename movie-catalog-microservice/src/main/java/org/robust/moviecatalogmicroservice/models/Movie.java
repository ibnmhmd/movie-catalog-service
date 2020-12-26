package org.robust.moviecatalogmicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
 private int id;
 private String name;
 private String description;
 private String actor;
 private String producor;
}
