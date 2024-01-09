package com.workintech.s19challenge.service.order;

import com.workintech.s19challenge.entity.order.Order;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long id) {
        Optional<Order> orderWithId= orderRepository.findById(id);
        if(orderWithId.isPresent()){
            return orderWithId.get();
        }else{
            throw new GlobalException("Failed to find the order related to the given id"+id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order deleteByIdOrder(long id) {
    Order order = findById(id);
    if(order != null ){
        orderRepository.delete(order);
        return order;
}else{
        throw new GlobalException("Failed to find the order related to the given id"+id, HttpStatus.BAD_REQUEST);
    }
    }
}
