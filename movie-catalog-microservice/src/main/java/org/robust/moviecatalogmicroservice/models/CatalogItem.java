package org.robust.moviecatalogmicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogItem {
  private Movie movie;
  private int rating;
}
