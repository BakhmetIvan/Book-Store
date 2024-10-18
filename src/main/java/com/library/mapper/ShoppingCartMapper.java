package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.shoppingcart.ShoppingCartResponseDto;
import com.library.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(source = "user.id", target = "userId")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
