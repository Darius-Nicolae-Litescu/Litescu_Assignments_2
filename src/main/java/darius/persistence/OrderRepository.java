package darius.persistence;

import darius.model.Order;

import java.util.List;

public interface OrderRepository {
    void createTable();

    void insert(Order order);

    List<Order> getAll(String username);
    
    void delete(String username);

}
