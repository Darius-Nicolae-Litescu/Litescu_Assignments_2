package darius.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private User orderedBy;
    private LocalDateTime orderedAt;
    private List<Product> productList;

    public Order() {
    }

    public Order(User orderedBy, LocalDateTime orderedAt) {
        this.orderedBy = orderedBy;
        this.orderedAt = orderedAt;
    }

    public Order(User orderedBy, List<Product> productList) {
        this.orderedBy = orderedBy;
        this.orderedAt = LocalDateTime.now();
        this.productList = productList;
    }

    public Order(User orderedBy, LocalDateTime orderedAt, List<Product> productList) {
        this.orderedBy = orderedBy;
        this.orderedAt = orderedAt;
        this.productList = productList;
    }

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderedBy, order.orderedBy) && Objects.equals(orderedAt, order.orderedAt) && Objects.equals(productList, order.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderedBy, orderedAt, productList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedBy=" + orderedBy +
                ", orderedAt=" + orderedAt +
                ", productList=" + productList +
                '}';
    }
}
