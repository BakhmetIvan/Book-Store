package com.library.repository.book;

import com.library.model.Book;
import com.library.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class AuthorsSpecificationProvider implements SpecificationProvider<Book> {
    private static final String AUTHOR_FIELD_NAME = "author";

    @Override
    public String getKey() {
        return AUTHOR_FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(AUTHOR_FIELD_NAME)
                .in(Arrays.stream(params).toArray());
    }
}
