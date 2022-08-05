package darius.database;

import darius.dto.AdminOrderViewDTO;
import darius.model.Order;
import darius.model.Product;
import darius.model.User;
import darius.model.UserRole;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

public class ShoppingDatabaseStatement {
    Statement statement;
    private final ShoppingDatabaseConnection connection;

    public ShoppingDatabaseStatement(ShoppingDatabaseConnection connection) {
        this.connection = connection;
    }

    public void execute(String sql, Object... params) {
        connection.connect();
        statement = connection.createStatement();
        if (statement != null) {
            execute(statement, sql, params);
            close();
        }
    }

    public boolean execute(Statement statement, String sql, Object... params) {
        try {
            sql = replaceParametersInSqlString(sql, params);
            System.out.println(sql);
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String replaceParametersInSqlString(String sql, Object... params) {
        if (params != null && params.length > 0) {
            for (Object param : params) {
                if (sql.contains("%")) {
                    sql = sql.replaceAll("\\%.*?\\%\\s?", "'%" + param + "%'");
                } else {
                    {
                        if (param instanceof String) {
                            sql = sql.replaceFirst("\\?", "\"" + param + "\"");
                        } else if (param instanceof Long || param instanceof Integer || param instanceof BigDecimal) {
                            sql = sql.replaceFirst("\\?", param.toString());
                        }
                    }
                }
            }
        }
        return sql;
    }


    public User executeGetUserByUsername(String sql, String username){
        User user = null;
        try {
            sql = replaceParametersInSqlString(sql, username);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                user = new User(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("security_question"),
                        resultSet.getString("security_question_answer"),
                        resultSet.getString("role").equals("ADMIN") ?
                        		UserRole.ADMIN : UserRole.USER,
                        resultSet.getString("user_location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return user;
    }

    public Product executeGetProductById(String sql, Long id) {
        Product product = null;
        try {
            sql = replaceParametersInSqlString(sql, String.valueOf(id));
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                product = new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("product_code"),
                        resultSet.getString("picture_href"),
                        resultSet.getBigDecimal("ron_price_per_unit"),
                        resultSet.getInt("tax_percentage"),
                        resultSet.getInt("available_quantity"),
                        resultSet.getString("category"),
                        resultSet.getInt("star_rating"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return product;
    }

    public List<AdminOrderViewDTO> executeViewOrdersForAdmin(String sql, Object ... params){
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql = replaceParametersInSqlString(sql, params);
            ResultSet resultSet = statement.executeQuery(sql);
            int resultCount = 0;
            if (resultSet.last()) {
                resultCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            System.out.println("Retrieving " + resultCount + " orders for admin");
            List<AdminOrderViewDTO> adminOrderViewDTOS = new ArrayList<>(resultCount);
            while (resultSet.next()) {
                AdminOrderViewDTO product = new AdminOrderViewDTO(resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getLong("product_id"),
                        resultSet.getString("ordered_at")
                        );
                adminOrderViewDTOS.add(product);
            }
            Set<AdminOrderViewDTO> uniqueElements = new HashSet<>(adminOrderViewDTOS);
            adminOrderViewDTOS.clear();
            adminOrderViewDTOS.addAll(uniqueElements);
            return adminOrderViewDTOS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return null;
    }
    
    public List<Product> executeGetMultipleProducts(String sql, Object... params) {
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql = replaceParametersInSqlString(sql, params);
            ResultSet resultSet = statement.executeQuery(sql);
            int resultCount = 0;
            if (resultSet.last()) {
                resultCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            System.out.println("Retrieving " + resultCount + " products");
            List<Product> productList = new ArrayList<>(resultCount);
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("product_code"),
                        resultSet.getString("picture_href"),
                        resultSet.getBigDecimal("ron_price_per_unit"),
                        resultSet.getInt("tax_percentage"),
                        resultSet.getInt("available_quantity"),
                        resultSet.getString("category"),
                        resultSet.getInt("star_rating"));
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return null;
    }

    public List<Order> executeGetOrders(String sql, Object... params) {
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql = replaceParametersInSqlString(sql, params);
            ResultSet resultSet = statement.executeQuery(sql);
            int resultCount = 0;
            if (resultSet.last()) {
                resultCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            System.out.println("Retrieving " + resultCount + " orders");
            List<Order> orderList = new ArrayList<>(resultCount);
            List<Product> productList =  new ArrayList<>();
            while (resultSet.next()) {
                //ordered_at`,`username`, `product_id
                User user = new User(resultSet.getString("username"));
                Product product = new Product(resultSet.getLong("product_id"));
                productList.add(product);
                LocalDateTime date = resultSet.getTimestamp("ordered_at").toLocalDateTime();
                Order order = new Order(user,date, productList);
                orderList.add(order);
            }
            Set<Order> uniqueOrders = new HashSet<>(orderList);
            orderList.clear();
            orderList.addAll(uniqueOrders);
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return null;
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}