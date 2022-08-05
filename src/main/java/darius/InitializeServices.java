package darius;

import darius.database.ShoppingDatabaseConnection;
import darius.database.ShoppingDatabaseStatement;
import darius.logger.Logger;
import darius.logger.console.ConsoleLogger;
import darius.persistence.AdminRepository;
import darius.persistence.AdminRepositoryImpl;
import darius.persistence.OrderRepository;
import darius.persistence.OrderRepositoryImpl;
import darius.persistence.ProductRepository;
import darius.persistence.ProductRepositoryImpl;
import darius.persistence.UserRepository;
import darius.persistence.UserRepositoryImpl;
import darius.service.AdminService;
import darius.service.AdminServiceImpl;
import darius.service.OrderService;
import darius.service.OrderServiceImpl;
import darius.service.ProductService;
import darius.service.ProductServiceImpl;
import darius.service.UserService;
import darius.service.UserServiceImpl;

public class InitializeServices {
	public static ProductService createProductServiceInstance() {
		ShoppingDatabaseConnection shoppingDatabaseConnection = new ShoppingDatabaseConnection();
        ShoppingDatabaseStatement shoppingDatabaseStatement = new ShoppingDatabaseStatement(shoppingDatabaseConnection);
        ProductRepository productRepository = new ProductRepositoryImpl(shoppingDatabaseStatement);
        ProductService productService = new ProductServiceImpl(productRepository);
        return productService;
	}
	
	public static Logger createConsoleLoggerInstance() {
		Logger logger = new ConsoleLogger();
		return logger;
	}
	
	public static UserService createUserServiceInstance() {
		ShoppingDatabaseConnection shoppingDatabaseConnection = new ShoppingDatabaseConnection();
        ShoppingDatabaseStatement shoppingDatabaseStatement = new ShoppingDatabaseStatement(shoppingDatabaseConnection);
        UserRepository userRepository = new UserRepositoryImpl(shoppingDatabaseStatement);
        UserService userService = new UserServiceImpl(userRepository);
        userService.createTable();
        return userService;
	}
	
	public static OrderService createOrderServiceInstance() {
        ShoppingDatabaseConnection shoppingDatabaseConnection = new ShoppingDatabaseConnection();
        ShoppingDatabaseStatement shoppingDatabaseStatement = new ShoppingDatabaseStatement(shoppingDatabaseConnection);
        OrderRepository orderRepository = new OrderRepositoryImpl(shoppingDatabaseStatement);
        OrderService orderService = new OrderServiceImpl(orderRepository);
        orderService.createTable();
        return orderService;
	}
	
	public static AdminService createAdminServiceInstance() {
		ShoppingDatabaseConnection shoppingDatabaseConnection = new ShoppingDatabaseConnection();
        ShoppingDatabaseStatement shoppingDatabaseStatement = new ShoppingDatabaseStatement(shoppingDatabaseConnection);
        AdminRepository adminRepository = new AdminRepositoryImpl(shoppingDatabaseStatement);
        AdminService adminService = new AdminServiceImpl(adminRepository);
        return adminService;
		
	}

}
