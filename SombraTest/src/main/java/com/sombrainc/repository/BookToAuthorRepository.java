package com.sombrainc.repository;

import com.sombrainc.entity.Author;
import com.sombrainc.entity.BookToAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookToAuthorRepository extends JpaRepository<BookToAuthor,Long>
{
  @Query("delete from BookToAuthor where author.id=:id")
  @Modifying
  void deleteAuthor(@Param("id")long id);

  @Query("delete from BookToAuthor where book.id=:id")
  @Modifying
  void deleteBook(@Param("id")long id);

  @Query("delete from BookToAuthor where id=:id")
  @Modifying
  void delete(@Param("id")long id);

  @Query("from BookToAuthor where book.id=:id")
  BookToAuthor findByBookId(@Param("id")long id);

  @Query("update BookToAuthor set book.id=:book , author.id=:author where id=:id")
  @Modifying
  void update(@Param("id")long id,@Param("book")long book,@Param("author")long author);

  @Query("from BookToAuthor where author in (:list)")
  List<BookToAuthor>findByList(@Param("list")List<Author>list);
}
