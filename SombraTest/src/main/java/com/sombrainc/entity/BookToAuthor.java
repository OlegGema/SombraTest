package com.sombrainc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_TO_AUTHOR")
@Data
public class BookToAuthor extends PersistableEntity
{
  @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
  private Book book;

  @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
  private Author author;

  public BookToAuthor()
  {
  }
}
