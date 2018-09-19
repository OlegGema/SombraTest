package com.sombrainc.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookToAuthorDTO
{
 private long bookId;
 private long authorId;
}
