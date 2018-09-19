package com.sombrainc.service;

import com.sombrainc.DTO.BookToAuthorDTO;
import com.sombrainc.DTO.BookToAuthorResponse;

import java.util.List;

public interface BookToAuthorService
{
  void save(BookToAuthorDTO author);
  List<BookToAuthorResponse> getAll();
  void update(long id,BookToAuthorDTO bookToAuthorDTO);
  void delete(long id);
}
