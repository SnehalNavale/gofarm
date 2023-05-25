package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.dto.UserData;
import com.app.entities.Customer;
import com.app.entities.DeliveryBoy;
import com.app.entities.Manager;
import com.app.entities.Role;
import com.app.entities.User;
import com.app.repository.CustomerRepo;
import com.app.repository.DeliveryBoyRepo;
import com.app.repository.ManagerRepo;

@Service
@Transactional
public class LoginServiceImp implements LoginService {
	
	@Autowired
	private DeliveryBoyRepo delBoyRepo;
	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private ManagerRepo manRepo;
	

	@Override
	public Object authenticate(UserDTO user) {
		System.out.println("in authenticate method of "+getClass());
		if (delBoyRepo.existsByEmail(user.getEmail())){
			User boy= delBoyRepo.findByEmail(user.getEmail()).orElseThrow();
			if(boy.getPassword().equals(user.getPassword())) {
				return new UserData(boy);
			}else {
				return "Authentication failed";
			}
		}
		else if(custRepo.existsByEmail(user.getEmail())) {
			User cust= custRepo.findByEmail(user.getEmail()).orElseThrow();
			if(cust.getPassword().equals(user.getPassword())) {
				return new UserData(cust);
			}else {
				return "Authentication failed";
			}
		}
		else if(manRepo.existsByEmail(user.getEmail())) {
			User manager= manRepo.findByEmail(user.getEmail()).orElseThrow();
			if(manager.getPassword().equals(user.getPassword())) {
				return new UserData(manager);
			}else {
				return "Authentication failed";
			}
		}else
			return "Invalid Email Id";
		
	}

}
