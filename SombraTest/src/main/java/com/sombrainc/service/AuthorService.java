package com.sombrainc.service;

import com.sombrainc.DTO.AuthorDTO;
import com.sombrainc.DTO.AuthorResponse;

import java.util.List;

public interface AuthorService
{
  void save(AuthorDTO author);
  List<AuthorResponse>getAll();
  void updateAuthor(long id,AuthorDTO authorDTO);
  void deleteAuthor(long id);
  List<AuthorResponse>sortByAge();
  AuthorResponse maxBooks();
}
