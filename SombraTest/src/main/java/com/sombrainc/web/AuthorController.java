package com.sombrainc.web;

import com.sombrainc.DTO.AuthorDTO;
import com.sombrainc.DTO.AuthorResponse;
import com.sombrainc.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController
{
  @Autowired
  private AuthorService authorService;

  @RequestMapping(value = "/author/save",method = RequestMethod.POST)
  public void saveAuthor(@RequestBody AuthorDTO author){
    authorService.save(author);
  }

  @RequestMapping(value = "/author/getAll",method = RequestMethod.GET)
  public List<AuthorResponse> findAll(){
    return authorService.getAll();
  }

  @RequestMapping(value = "/author/update/{authorId}",method = RequestMethod.PATCH)
  public void updateAuthor(@PathVariable("authorId")long id,@RequestBody AuthorDTO authorDTO){
    authorService.updateAuthor(id, authorDTO);
  }

  @RequestMapping(value = "/author/delete/{authorId}",method = RequestMethod.DELETE)
  public void deleteAuthor(@PathVariable("authorId")long id){
    authorService.deleteAuthor(id);
  }

  //Show authors which are older 55 years old and sort them by `born` column
  @RequestMapping(value = "/author/sortByAge",method = RequestMethod.GET)
  public List<AuthorResponse> sortByAge(){
    return authorService.sortByAge();
  }

  //Find out author which has most books
  @RequestMapping(value = "/author/maxBooks",method = RequestMethod.GET)
  public AuthorResponse maxBooks(){
    return authorService.maxBooks();
  }


}
