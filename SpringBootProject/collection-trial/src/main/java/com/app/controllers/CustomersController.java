package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BillDTO;
import com.app.dto.ExtraDTO;
import com.app.dto.MessageDTO;
import com.app.dto.UnbilledDTO;
import com.app.dto.WithdrawDTO;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/gofarm")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomersController {
	@Autowired
	private CustomerService custService;
	
	public CustomersController() {
		System.out.println("IN ctor of "+getClass());
	}
	
	@GetMapping("/unbilled/{id}")
	public UnbilledDTO getUnbilled(@PathVariable long id) {
		return custService.getUnbilled(id);
	}
	
	@GetMapping("/bills/{id}")
	public List<BillDTO> getBills(@PathVariable long id){
		return custService.getBillHistory(id);
	}
	
	@GetMapping("/leaves/{id}")
	public List<WithdrawDTO> getLeaves(@PathVariable long id){
		return custService.getLeavesForId(id);
	}
	
	@GetMapping("/extras/{id}")
	public List<ExtraDTO> getExtras(@PathVariable long id){
		return custService.getExtrasForId(id);
	}

	@PostMapping("/extras/{id}")
	public ExtraDTO postExtra(@PathVariable long id, @RequestBody ExtraDTO extra) {
		System.out.println("Extra Date "+extra.getExtraDate().toString()+",Packets count "+extra.getPacketsCount());
		ExtraDTO dto = custService.postExtr(id,extra);
		System.out.println("id "+dto.getId()+", "+dto.getExtraDate().toString()+", "+dto.isDelivered()+"");
		return dto;
	}
	
	@PostMapping("/leaves/{id}")
	public WithdrawDTO postLeave(@PathVariable long id, @RequestBody WithdrawDTO withdrawDTO) {
		WithdrawDTO dto = custService.postLeave(id,withdrawDTO);
		return dto;
	}
	
	@GetMapping("/invalidleaves")
	public List<WithdrawDTO> getAllToValidate() {
		return custService.getInvalidLeaves();
	}
	
	@GetMapping("/aproveleave/{id}")
	public MessageDTO approveLeave(@PathVariable Long id) {
		return custService.approveLeave(id);
	}
}
