package com.sombrainc.service;

import com.sombrainc.DTO.BookDTO;
import com.sombrainc.DTO.BookResponse;

import java.util.HashMap;
import java.util.List;

public interface BookService
{
  void save(BookDTO bookDTO);
  List<BookResponse> getAll();
  void updateBook(long id,BookDTO bookDTO);
  void deleteBook(long id);
  List<BookDTO>moreThanOne();
  HashMap<String,Integer>findByGenre();
}
