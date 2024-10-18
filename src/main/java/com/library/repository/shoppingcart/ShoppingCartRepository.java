package com.library.repository.shoppingcart;

import com.library.model.ShoppingCart;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUser(User user);
}
