package com.sombrainc.service.impl;

import com.sombrainc.DTO.AuthorDTO;
import com.sombrainc.DTO.AuthorMax;
import com.sombrainc.DTO.AuthorResponse;
import com.sombrainc.entity.Author;
import com.sombrainc.repository.AuthorRepository;
import com.sombrainc.repository.BookToAuthorRepository;
import com.sombrainc.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService
{
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private BookToAuthorRepository bookToAuthorRepository;

  @Override
  public void save(AuthorDTO author)
  {
    Author author1=new Author();
    author1.setBorn(author.getBorn());
    author1.setGender(author.getGender());
    author1.setName(author.getName());
    authorRepository.save(author1);
  }

  @Override
  public List<AuthorResponse> getAll()
  {
    List<Author>authors=authorRepository.findAll();
    List<AuthorResponse>authorResponses=new ArrayList<AuthorResponse>();
    authors.forEach(el->{
      authorResponses.add(AuthorResponse.builder().born(el.getBorn()).gender(el.getGender()).name(el.getName()).id(el.getId()).build());
    });

    return authorResponses;
  }

  @Override
  @Transactional
  public void updateAuthor(long id, AuthorDTO authorDTO)
  {
    if (authorRepository.findById(id)!=null){
      authorRepository.updateAuthor(id,authorDTO.getName(),authorDTO.getGender(),authorDTO.getBorn());
    }else
      throw new IllegalArgumentException("wrong author id");
  }

  @Override
  @Transactional
  public void deleteAuthor(long id)
  {
    bookToAuthorRepository.deleteAuthor(id);
    authorRepository.deleteAuthor(id);
  }

  @Override
  public List<AuthorResponse> sortByAge()
  {
    Calendar calendar=Calendar.getInstance();
    calendar.set(Calendar.YEAR,1963);
    Date date=calendar.getTime();
    List<Author> authors = authorRepository.sortByAge(date);
    List<AuthorResponse>authorResponses=new ArrayList<>();
    authors.forEach(el->{
      authorResponses.add(AuthorResponse.builder().id(el.getId()).born(el.getBorn()).name(el.getName()).gender(el.getGender()).build());
    });
    return authorResponses;
  }

  @Override
  public AuthorResponse maxBooks()
  {
    List<Author> all = authorRepository.findAll();
    List<AuthorMax>authorResponses=new ArrayList<>();
    all.forEach(el->{
      authorResponses.add(AuthorMax.builder().id(el.getId()).books(el.getBookToAuthors().size()).build());
    });

    AuthorMax authorMax = authorResponses.stream().max(Comparator.comparing(el -> el.getBooks())).get();
    Author byId = authorRepository.findById(authorMax.getId());
    return AuthorResponse.builder().id(byId.getId()).born(byId.getBorn()).gender(byId.getGender()).name(byId.getName()).build();
  }

}
