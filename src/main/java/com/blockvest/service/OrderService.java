package com.blockvest.service;


import com.blockvest.domain.OrderType;
import com.blockvest.model.Coin;
import com.blockvest.model.Order;
import com.blockvest.model.OrderItem;
import com.blockvest.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType OrderType, String assetSymbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;

}
