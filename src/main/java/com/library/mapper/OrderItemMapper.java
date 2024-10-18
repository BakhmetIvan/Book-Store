package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.order.OrderItemResponseDto;
import com.library.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
