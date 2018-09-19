package com.sombrainc.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookToAuthorResponse
{
  private long id;
  private String bookName;
  private String  AuthorName;
}
