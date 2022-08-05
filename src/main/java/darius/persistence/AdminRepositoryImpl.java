package darius.persistence;

import darius.database.ShoppingDatabaseStatement;
import darius.dto.AdminOrderViewDTO;
import darius.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    private final ShoppingDatabaseStatement shoppingDatabaseStatement;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AdminRepositoryImpl(ShoppingDatabaseStatement shoppingDatabaseStatement) {
        this.shoppingDatabaseStatement = shoppingDatabaseStatement;
    }

    @Override
    public List<AdminOrderViewDTO> viewOrdersByExactDate(String exactDate){
        String sql = "SELECT user.id as user_id, user.username as username, orderTable.product_id as product_id, orderTable.ordered_at as ordered_at FROM `User` " +
                "user INNER JOIN `Order` as orderTable on user.username = orderTable.username " +
                "WHERE orderTable.ordered_at = ?";

        return this.shoppingDatabaseStatement.executeViewOrdersForAdmin(sql, exactDate);
    }

    @Override
    public List<AdminOrderViewDTO> viewOrdersByUserLocation(String userLocation){
        String sql = "SELECT user.id as user_id, user.username as username, orderTable.product_id as product_id, orderTable.ordered_at as ordered_at FROM `User` " +
                "user INNER JOIN `Order` as orderTable on user.username = orderTable.username " +
                "WHERE user.user_location = ?";

        return this.shoppingDatabaseStatement.executeViewOrdersForAdmin(sql, userLocation);
    }

}
