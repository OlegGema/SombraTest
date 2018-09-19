package com.sombrainc.service.impl;

import com.sombrainc.DTO.BookDTO;
import com.sombrainc.DTO.BookResponse;
import com.sombrainc.entity.Author;
import com.sombrainc.entity.Book;
import com.sombrainc.entity.BookToAuthor;
import com.sombrainc.repository.AuthorRepository;
import com.sombrainc.repository.BookRepository;
import com.sombrainc.repository.BookToAuthorRepository;
import com.sombrainc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private BookToAuthorRepository bookToAuthorRepository;
  @Autowired
  private AuthorRepository authorRepository;

  @Override
  public void save(BookDTO bookDTO)
  {
    Book book=new Book();
    book.setName(bookDTO.getName());
    book.setGenre(bookDTO.getGenre());
    book.setPublished(bookDTO.getPublished());
    book.setRating(bookDTO.getRating());
    bookRepository.save(book);
  }

  @Override
  public List<BookResponse> getAll()
  {
    List<Book>authors=bookRepository.findAll();
    List<BookResponse>authorResponses=new ArrayList<BookResponse>();
    authors.forEach(el->{
      authorResponses.add(BookResponse.builder().name(el.getName()).genre(el.getGenre()).rating(el.getRating()).id(el.getId()).published(el.getPublished()).build());
    });

    return authorResponses;
  }

  @Override
  @Transactional
  public void updateBook(long id, BookDTO bookDTO)
  {
    if (bookRepository.findById(id)!=null){
      bookRepository.updateBook(id,bookDTO.getName(),bookDTO.getGenre(),bookDTO.getPublished(),bookDTO.getRating());
    }else
      throw new IllegalArgumentException("wrong book id");

  }

  @Override
  @Transactional
  public void deleteBook(long id)
  {
    bookToAuthorRepository.deleteBook(id);
    bookRepository.deleteBook(id);
  }

  @Override
  public List<BookDTO> moreThanOne()
  {
    List<Author> all = authorRepository.findAll();
    List<Author>authors=all.stream().filter(el->el.getBookToAuthors().size()>1).collect(Collectors.toList());

    List<BookToAuthor> byList = bookToAuthorRepository.findByList(authors);

    List<Long>list=new ArrayList<>();
    byList.forEach(el->{
      list.add(el.getBook().getId());
    });
    return bookRepository.findByList(list);
  }

  @Override
  public HashMap<String, Integer> findByGenre()
  {
    List<Book> all = bookRepository.findAll();
    HashMap<String ,Integer>hashMap=new HashMap<>();

    all.forEach(el->{
      if (!hashMap.containsKey(el.getGenre())){
        hashMap.put(el.getGenre(),1);
      }else
        hashMap.put(el.getGenre(),hashMap.get(el.getGenre())+1);
    });

    return hashMap;
  }
}
