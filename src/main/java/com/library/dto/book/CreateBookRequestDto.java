package com.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Accessors(chain = true)
public class CreateBookRequestDto {
        @NotBlank
        @Size(max = 255)
        String title;
        @NotBlank
        @Size(max = 255)
        String author;
        @NotBlank
        @Size(max = 255)
        String isbn;
        @NotNull
        @Positive
        BigDecimal price;
        Set<Long> categoryIds;
        @Size(max = 255)
        String description;
        @Size(max = 255)
        String coverImage;
}
