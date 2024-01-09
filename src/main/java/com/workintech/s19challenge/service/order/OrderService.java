package com.workintech.s19challenge.service.order;

import com.workintech.s19challenge.entity.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(long id);
    Order saveOrder(Order order);
   Order deleteByIdOrder(long id);

}
