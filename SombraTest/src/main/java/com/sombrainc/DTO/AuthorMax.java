package com.sombrainc.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorMax
{
  private long id;
  private int books;
}
