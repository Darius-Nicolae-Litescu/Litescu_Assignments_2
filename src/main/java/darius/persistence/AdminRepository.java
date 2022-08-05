package darius.persistence;

import darius.dto.AdminOrderViewDTO;

import java.util.List;

public interface AdminRepository {
    List<AdminOrderViewDTO> viewOrdersByExactDate(String exactDate);

	List<AdminOrderViewDTO> viewOrdersByUserLocation(String userLocation);
}
