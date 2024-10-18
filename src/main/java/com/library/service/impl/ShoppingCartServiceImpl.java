package com.library.service.impl;

import com.library.dto.shoppingcart.CartItemRequestDto;
import com.library.dto.shoppingcart.CartItemUpdateRequestDto;
import com.library.dto.shoppingcart.ShoppingCartResponseDto;
import com.library.mapper.ShoppingCartMapper;
import com.library.model.Book;
import com.library.model.CartItem;
import com.library.model.ShoppingCart;
import com.library.model.User;
import lombok.RequiredArgsConstructor;
import com.library.exception.EntityNotFoundException;
import com.library.repository.book.BookRepository;
import com.library.repository.shoppingcart.CartItemRepository;
import com.library.repository.shoppingcart.ShoppingCartRepository;
import com.library.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto saveCartItem(CartItemRequestDto requestDto, User user) {
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: "
                        + requestDto.getBookId())
        );
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto getShoppingCart(User user) {
        return shoppingCartMapper.toDto(shoppingCartRepository.findShoppingCartByUser(user));
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantity(Long id, CartItemUpdateRequestDto requestDto, User user) {
        ShoppingCart cart = shoppingCartRepository.findShoppingCartByUser(user);
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(id, cart.getId())
                .map(item -> {
                    item.setQuantity(requestDto.getQuantity());
                    return item;
                }).orElseThrow(() -> new EntityNotFoundException(
                        String.format("No cart item with id: %d for user: %d",
                                id, user.getId())
                ));
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(cart);
    }

    @Transactional
    @Override
    public void deleteCartItemById(Long id, User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(id, shoppingCart.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("No cart item with id: %d for user: %d",
                                        id, user.getId())
                        ));
        cartItemRepository.delete(cartItem);
    }
}
