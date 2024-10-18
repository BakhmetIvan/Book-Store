package com.library.service.impl;

import com.library.dto.book.BookDtoWithoutCategoryIds;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.BookSearchParametersDto;
import com.library.dto.book.CreateBookRequestDto;
import com.library.mapper.BookMapper;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.exception.EntityNotFoundException;
import com.library.repository.book.BookRepository;
import com.library.repository.book.BookSpecificationBuilder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookResponseDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find book by id: " + id)
                );
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseDto updateBookInfoById(Long id, CreateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id " + id)
        );
        bookMapper.updateBookFromDto(requestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookResponseDto> search(BookSearchParametersDto searchParameters, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification, pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoriesId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoriesId(id, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }
}
