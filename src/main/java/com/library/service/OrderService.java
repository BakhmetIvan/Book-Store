package com.library.service;

import com.library.dto.order.OrderItemResponseDto;
import com.library.dto.order.OrderRequestDto;
import com.library.dto.order.OrderResponseDto;
import com.library.dto.order.OrderUpdateStatusDto;
import com.library.model.User;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(User user, OrderRequestDto requestDto);

    List<OrderResponseDto> findAllOrders(User user, Pageable pageable);

    OrderResponseDto updateOrderStatus(Long id, OrderUpdateStatusDto requestDto);

    List<OrderItemResponseDto> findAllOrderItems(Long id, Pageable pageable, User user);

    OrderItemResponseDto findOrderItemByOrderId(Long orderId, Long itemId, User user);
}
