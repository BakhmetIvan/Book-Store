package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.order.OrderRequestDto;
import com.library.dto.order.OrderResponseDto;
import com.library.dto.order.OrderUpdateStatusDto;
import com.library.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    Order toModel(OrderRequestDto responseDto);

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toDto(Order order);

    void updateOrderFromDto(@MappingTarget Order order, OrderUpdateStatusDto orderRequestDto);
}
