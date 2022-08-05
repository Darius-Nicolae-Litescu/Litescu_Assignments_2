package darius.service;

import darius.model.Order;
import darius.persistence.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void createTable() {
        orderRepository.createTable();

    }

    @Override
    public void insert(Order order) {
        orderRepository.insert(order);

    }

    @Override
    public List<Order> getAll(String username) {
        return orderRepository.getAll(username);
    }
    
    @Override
    public void delete(String username) {
    	orderRepository.delete(username);
    }
}
