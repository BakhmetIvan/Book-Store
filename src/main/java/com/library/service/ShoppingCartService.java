package com.library.service;

import com.library.dto.shoppingcart.CartItemRequestDto;
import com.library.dto.shoppingcart.CartItemUpdateRequestDto;
import com.library.dto.shoppingcart.ShoppingCartResponseDto;
import com.library.model.User;

public interface ShoppingCartService {
    void createShoppingCart(User user);

    ShoppingCartResponseDto saveCartItem(CartItemRequestDto requestDto, User user);

    ShoppingCartResponseDto getShoppingCart(User user);

    ShoppingCartResponseDto updateQuantity(Long id, CartItemUpdateRequestDto requestDto, User user);

    void deleteCartItemById(Long id, User user);
}
