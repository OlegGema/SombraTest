package com.sombrainc.web;

import com.sombrainc.DTO.BookToAuthorDTO;
import com.sombrainc.DTO.BookToAuthorResponse;
import com.sombrainc.service.BookToAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookToAuthorController
{
  @Autowired
  BookToAuthorService bookToAuthorService;

  @RequestMapping(value = "/connection/save",method = RequestMethod.POST)
  public void saveAuthor(@RequestBody BookToAuthorDTO bookToAuthorDTO){
    bookToAuthorService.save(bookToAuthorDTO);
  }

  @RequestMapping(value = "/connection/getAll",method = RequestMethod.GET)
  public List<BookToAuthorResponse> findAll(){
    return bookToAuthorService.getAll();
  }

  @RequestMapping(value = "/connection/update/{Id}",method = RequestMethod.PATCH)
  public void update(@PathVariable("Id")long id,@RequestBody BookToAuthorDTO bookToAuthorDTO){

    bookToAuthorService.update(id, bookToAuthorDTO);

  }

  @RequestMapping(value = "/connection/delete/{Id}",method = RequestMethod.DELETE)
  public void deleteAuthor(@PathVariable("Id")long id){
    bookToAuthorService.delete(id);
  }
}
