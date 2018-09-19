package com.sombrainc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class PersistableEntity implements Serializable
{

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    PersistableEntity that = (PersistableEntity) o;

    return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode()
  {
    return id != null ? id.hashCode() : 0;
  }
}
