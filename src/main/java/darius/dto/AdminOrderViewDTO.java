package darius.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class AdminOrderViewDTO {
    private Long userId;
    private String username;
    private Long productId;
    private String orderedAt;

    public AdminOrderViewDTO(Long userId, String username, Long productId, String orderedAt) {
        this.userId = userId;
        this.username = username;
        this.productId = productId;
        this.orderedAt = orderedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(String orderedAt) {
        this.orderedAt = orderedAt;
    }

	@Override
	public int hashCode() {
		return Objects.hash(orderedAt, productId, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminOrderViewDTO other = (AdminOrderViewDTO) obj;
		return Objects.equals(orderedAt, other.orderedAt) && Objects.equals(productId, other.productId)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
	}
    
    
}
