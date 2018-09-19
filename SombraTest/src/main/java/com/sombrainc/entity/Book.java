package com.sombrainc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BOOK")
@Data
public class Book extends PersistableEntity
{
  @Column(name = "NAME")
  private String name;

  @Column(name = "PUBLISHED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date published;

  @Column(name = "GENRE")
  private String genre;

  @Column(name = "RATING")
  private String rating;

  @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "book")
  private List<BookToAuthor> bookToAuthors;

  public Book()
  {
  }
}
