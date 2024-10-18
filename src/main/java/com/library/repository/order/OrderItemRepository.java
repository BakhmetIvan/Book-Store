package com.library.repository.order;

import com.library.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long id, Pageable pageable);

    OrderItem findByIdAndOrderId(Long itemId, Long orderId);
}
