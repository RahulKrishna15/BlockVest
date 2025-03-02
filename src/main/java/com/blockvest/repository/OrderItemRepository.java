package com.blockvest.repository;

import com.blockvest.model.Order;
import com.blockvest.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
