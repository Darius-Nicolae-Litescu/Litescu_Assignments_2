package darius.persistence;

import darius.database.ShoppingDatabaseStatement;
import darius.model.Order;
import darius.model.Product;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final ShoppingDatabaseStatement shoppingDatabaseStatement;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public OrderRepositoryImpl(ShoppingDatabaseStatement shoppingDatabaseStatement) {
        this.shoppingDatabaseStatement = shoppingDatabaseStatement;
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE  IF NOT EXISTS  `Order` (" +
                "`id` BIGINT(20) NOT NULL AUTO_INCREMENT," +
                "`ordered_at` DATE NOT NULL," +
                "`username` VARCHAR(255) NOT NULL," +
                "`product_id` BIGINT(20) NOT NULL," +
                "PRIMARY KEY (`id`)" +
                ");";

        shoppingDatabaseStatement.execute(sql, "");
    }


    @Override
    public void insert(Order order) {
        String sql = "INSERT INTO `Order`(`id`, `ordered_at`, `username`, `product_id`) " +
                "VALUES (NULL,?,?,?)";
        if(order.getProductList()!= null && order.getProductList().size() > 0) {
            for (Product product : order.getProductList()) {
                shoppingDatabaseStatement.execute(
                        sql,
                        formatter.format(order.getOrderedAt()),
                        order.getOrderedBy().getUsername(),
                        product.getId());
            }
        }
    }

    @Override
    public List<Order> getAll(String username) {
        String sql = "SELECT * FROM `Order` WHERE username=?";
        return shoppingDatabaseStatement.executeGetOrders(sql, username);
    }
    
    @Override
    public void delete(String username) {
        String sql = "DELETE FROM `Order` WHERE username=?";
        shoppingDatabaseStatement.execute(sql, username);
    }


}
