package darius.service;

import darius.model.Order;

import java.util.List;


public interface OrderService {
    void createTable();

    void insert(Order order);

    List<Order> getAll(String username);
    
    void delete(String username);

}
