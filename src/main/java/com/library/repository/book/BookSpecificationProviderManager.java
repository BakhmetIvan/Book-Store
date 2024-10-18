package com.library.repository.book;

import com.library.model.Book;
import com.library.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import com.library.repository.SpecificationProvider;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviderList;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviderList.stream()
                .filter(specProvider -> specProvider.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find specifications or key " + key));
    }
}
