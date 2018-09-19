package com.sombrainc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@Data
public class Author extends PersistableEntity
{
  @Column(name = "NAME")
  private String name;

  @Column(name = "GENDER")
  private String gender;

  @Column(name = "BORN")
  @Temporal(TemporalType.TIMESTAMP)
  private Date born;

  @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "author")
  private List<BookToAuthor>bookToAuthors;

  public Author()
  {
  }
}
