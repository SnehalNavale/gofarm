package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AssignLineDTO;
import com.app.dto.DeliveryDTO;
import com.app.dto.LineDTO;
import com.app.dto.LineOrderDTO;
import com.app.dto.MessageDTO;
import com.app.entities.DeliveryStatus;
import com.app.service.LineOrderService;

@RestController
@RequestMapping("/gofarm")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	@Autowired
	private LineOrderService lineOrderService;
	
	public AdminController() {
		System.out.println("IN ctor of "+getClass());
	}
	
	@GetMapping("/createdlineorders")
	public List<LineOrderDTO> getAllCreated(){
		System.out.println("In get all of "+getClass());
		return lineOrderService.getAllCreated();
	}
	
	@GetMapping("/createorders")
	public MessageDTO createOrders() {
		return new MessageDTO(lineOrderService.createOrders());
	}
	
	@GetMapping("/activateOrder/{id}")
	public MessageDTO activateOrde(@PathVariable Long id) {
		return new MessageDTO(lineOrderService.activateOrder(id));
	}

	@GetMapping("/orderdetails/{id}")
	public List<DeliveryDTO> getOrderDetails(@PathVariable Long id){
		return lineOrderService.getAllDeliveries(id);
	}
	
	@GetMapping("/lineorder/{id}")
	public LineOrderDTO getSingleLineOrder(@PathVariable Long id) {
		return lineOrderService.getSingleLineOrder(id);
	}
	
	@GetMapping("/updatedelstatus/{id}")
	public DeliveryStatus updateStatus(@PathVariable Long id) {
		return lineOrderService.updateDeliveryStatus(id);
	}
	
	@GetMapping("/fetchlines")
	public List<LineDTO> fetchAllLineNames(){
		return lineOrderService.fetchLines();
	}
	
	@PostMapping("/assignline")
	public MessageDTO assignLine(@RequestBody AssignLineDTO data) {
		return lineOrderService.assignLine(data.getId(),data.getLineName());
	}
}
