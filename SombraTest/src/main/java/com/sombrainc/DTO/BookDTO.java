package com.sombrainc.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookDTO
{
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
  private Date published;
  private String genre;
  private String rating;

  public BookDTO(String name, Date published, String genre, String rating)
  {
    this.name = name;
    this.published = published;
    this.genre = genre;
    this.rating = rating;
  }

  public BookDTO()
  {
  }
}
