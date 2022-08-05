package darius.service;

import darius.dto.AdminOrderViewDTO;
import darius.persistence.AdminRepository;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public List<AdminOrderViewDTO> viewOrdersByExactDate(String exactDate){
        return this.adminRepository.viewOrdersByExactDate(exactDate);
    }


	@Override
	public List<AdminOrderViewDTO> viewOrdersByUserLocation(String userLocation) {
		return this.adminRepository.viewOrdersByUserLocation(userLocation);
	}

}
