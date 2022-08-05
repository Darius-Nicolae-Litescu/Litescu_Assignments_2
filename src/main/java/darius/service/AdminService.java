package darius.service;

import darius.dto.AdminOrderViewDTO;

import java.util.List;

public interface AdminService {
    List<AdminOrderViewDTO> viewOrdersByExactDate(String exactDate);
    
	List<AdminOrderViewDTO> viewOrdersByUserLocation(String userLocation);

}
