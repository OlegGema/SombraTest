package com.sombrainc.web;

import com.sombrainc.DTO.BookDTO;
import com.sombrainc.DTO.BookResponse;
import com.sombrainc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookController
{
  @Autowired
  private BookService bookService;

  @RequestMapping(value = "/book/save",method = RequestMethod.POST)
  public void saveBook(@RequestBody BookDTO bookDTO){
    bookService.save(bookDTO);
  }

  @RequestMapping(value = "/book/getAll",method = RequestMethod.GET)
  public List<BookResponse> findAll(){
    return bookService.getAll();
  }

  @RequestMapping(value = "/book/update/{bookId}",method = RequestMethod.PATCH)
  public void updateBook(@PathVariable("bookId")long id,@RequestBody BookDTO bookDTO){
    bookService.updateBook(id, bookDTO);
  }

  @RequestMapping(value = "/book/delete/{bookId}",method = RequestMethod.DELETE)
  public void deleteBook(@PathVariable("bookId")long id){
    bookService.deleteBook(id);
  }

  //Return books whose author has more than 1 written books
  @RequestMapping(value = "/book/moreThanOneBook",method = RequestMethod.GET)
  public List<BookDTO> moreThanOneBook(){
    return bookService.moreThanOne();
  }

  //Calculate number of books by genre
  @RequestMapping(value = "/book/findByGenre",method = RequestMethod.GET)
  public HashMap<String ,Integer> dsa(){
    return bookService.findByGenre();
  }
}
