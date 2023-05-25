package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BillDTO;
import com.app.dto.MessageDTO;
import com.app.service.BillService;

@RestController
@RequestMapping("/gofarm")
@CrossOrigin(origins = "http://localhost:3000")
public class BillsController {
	
	@Autowired
	private BillService billService;
	
	@GetMapping("/getbillsagent/{id}")
	public List<BillDTO> getBillsAgent(@PathVariable long id){
		return billService.getAllForAgent(id);
	}
	
	@GetMapping("/updatebill/{id}")
	public boolean updateBillMarkPaid(@PathVariable long id) {
		return billService.markPaid(id);
	}
	
	@GetMapping("/getbillsadmin")
	public List<BillDTO> getBillsAdmin(){
		return billService.getAllBills();
	}
	
	@GetMapping("/generatebills")
	public MessageDTO generateBills() {
		return billService.generateAllBills();
	}
}
