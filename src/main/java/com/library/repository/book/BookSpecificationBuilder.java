package com.library.repository.book;

import com.library.dto.book.BookSearchParametersDto;
import com.library.model.Book;
import com.library.repository.SpecificationBuilder;
import com.library.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String TITLE_SPECIFICATION = "title";
    private static final String AUTHOR_SPECIFICATION = "author";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = Specification.where(null);
        if (searchParameters.getTitles() != null && searchParameters.getTitles().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE_SPECIFICATION)
                    .getSpecification(searchParameters.getTitles()));
        }
        if (searchParameters.getAuthors() != null && searchParameters.getAuthors().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR_SPECIFICATION)
                    .getSpecification(searchParameters.getAuthors()));
        }
        return specification;
    }
}
