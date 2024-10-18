package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.shoppingcart.CartItemDto;
import com.library.dto.shoppingcart.CartItemRequestDto;
import com.library.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(source = "bookId", target = "book.id")
    CartItem toModel(CartItemRequestDto cartItemRequestDto);
}
