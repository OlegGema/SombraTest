package com.sombrainc.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthorResponse
{
  private Long id;
  private String name;
  private String gender;
  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
  private Date born;
}
