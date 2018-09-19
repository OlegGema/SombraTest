package com.sombrainc.repository;

import com.sombrainc.DTO.BookDTO;
import com.sombrainc.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>
{
  @Query("from Book a where a.id=:id")
  Book findById(@Param("id")long id);

  @Query("update Book set name=:name , genre=:genre , published=:published, rating=:rating where id=:id")
  @Modifying
  void updateBook(@Param("id")long id,@Param("name")String name,@Param("genre")String genre,@Param("published")Date published,@Param("rating")String rating);

  @Query("delete from Book where id=:id")
  @Modifying
  void deleteBook(@Param("id")long id);

  @Query("select new com.sombrainc.DTO.BookDTO(name , published , genre , rating) from Book where id in (:list)")
  List<BookDTO>findByList(@Param("list")List<Long>list);

}
