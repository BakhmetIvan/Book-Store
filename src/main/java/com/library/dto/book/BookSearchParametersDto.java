package com.library.dto.book;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookSearchParametersDto {
        String[] titles;
        String[] authors;
}
