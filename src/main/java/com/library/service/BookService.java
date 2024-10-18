package com.library.service;

import com.library.dto.book.BookDtoWithoutCategoryIds;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.BookSearchParametersDto;
import com.library.dto.book.CreateBookRequestDto;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto requestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    void deleteById(Long id);

    BookResponseDto updateBookInfoById(Long id, CreateBookRequestDto requestDto);

    List<BookResponseDto> search(BookSearchParametersDto searchParameters, Pageable pageable);

    List<BookDtoWithoutCategoryIds> findAllByCategoriesId(Long id, Pageable pageable);
}
