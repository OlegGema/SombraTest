package com.sombrainc.service.impl;

import com.sombrainc.DTO.BookToAuthorDTO;
import com.sombrainc.DTO.BookToAuthorResponse;
import com.sombrainc.entity.Author;
import com.sombrainc.entity.Book;
import com.sombrainc.entity.BookToAuthor;
import com.sombrainc.repository.AuthorRepository;
import com.sombrainc.repository.BookRepository;
import com.sombrainc.repository.BookToAuthorRepository;
import com.sombrainc.service.BookToAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookToAuthorServiceImpl implements BookToAuthorService
{
  @Autowired
  private BookToAuthorRepository bookToAuthorRepository;
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private BookRepository bookRepository;


  @Override
  public void save(BookToAuthorDTO bookToAuthorDTO)
  {
    Author author=authorRepository.findById(bookToAuthorDTO.getAuthorId());
    Book book=bookRepository.findById(bookToAuthorDTO.getBookId());
    if(author!=null && book!=null){
      BookToAuthor byBookId = bookToAuthorRepository.findByBookId(bookToAuthorDTO.getBookId());
      if (byBookId!=null){
        byBookId.setAuthor(author);
        bookToAuthorRepository.save(byBookId);
      }else {
        BookToAuthor bookToAuthor=new BookToAuthor();
        bookToAuthor.setAuthor(author);
        bookToAuthor.setBook(book);
        bookToAuthorRepository.save(bookToAuthor);
      }
    }else
      throw new IllegalArgumentException("wrong book or author id`s");
  }

  @Override
  public List<BookToAuthorResponse> getAll()
  {
    List<BookToAuthorResponse>bookToAuthorResponses=new ArrayList<>();
    List<BookToAuthor> all = bookToAuthorRepository.findAll();

    all.forEach(el->{
      bookToAuthorResponses.add(BookToAuthorResponse.builder().id(el.getId()).AuthorName(el.getAuthor().getName()).bookName(el.getBook().getName()).build());
    });
    return bookToAuthorResponses;
  }

  @Override
  @Transactional
  public void update(long id, BookToAuthorDTO bookToAuthorDTO)
  {
    bookToAuthorRepository.update(id,bookToAuthorDTO.getBookId(),bookToAuthorDTO.getAuthorId());
  }

  @Override
  @Transactional
  public void delete(long id)
  {
    bookToAuthorRepository.delete(id);
  }
}
