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

import com.app.dto.AliasDTO;
import com.app.dto.MessageDTO;
import com.app.dto.NewSubscriptionDTO;
import com.app.dto.SubscriptionDTO;
import com.app.dto.ToggleSequenceDTO;
import com.app.dto.UnbilledDTO;
import com.app.entities.Bill;
import com.app.entities.Subscription;
import com.app.service.CustomerService;
import com.app.service.SubscriptionServive;

@RestController
@RequestMapping("/gofarm")
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionServive subService;
	
	public SubscriptionController() {
		System.out.println("IN ctor of "+getClass());
	}
	
	@PostMapping("/subscription")
	public Subscription createNew(@RequestBody NewSubscriptionDTO sub) {
		System.out.println("In Create New method of "+getClass());
		return subService.createSubscription(sub.getSub());
	}
	
	@GetMapping("/subscription")
	public List<SubscriptionDTO> getAll(){
		System.out.println("In get all of "+getClass());
		return subService.getAll();
	}
	
	@GetMapping("/subscription/{id}")
	public SubscriptionDTO getSingle(@PathVariable long id) {
		return subService.getsingleSubscription(id);
	}
	
	@PostMapping("/toggleseq/{id}")
	public ToggleSequenceDTO toggleSeq(@PathVariable long id, @RequestBody ToggleSequenceDTO updateInfo) {
		return subService.swapSequensers(id, updateInfo);
	}
	
	@GetMapping("/getline/{id}")
	public List<SubscriptionDTO> getListForToggleSeq(@PathVariable long id){
		return subService.getListForRearrange(id);
	}
	
	@GetMapping("/invalidcusts")
	public List<SubscriptionDTO> getListToAssignLine(){
		return subService.getCreatedCust();
	}
	
	@GetMapping("/fetchSubs/{id}")
	public List<SubscriptionDTO> getSubsForAgent(@PathVariable long id){
		return subService.getSubsForDeliveryAgent(id);
	}
	
	@PostMapping("/assignseq/{id}")
	public MessageDTO assignSequencer(@PathVariable long id, @RequestBody AliasDTO aliasDTO ) {
		//System.out.println("dto received in controller contains target is as"+aliasDTO.getSubid()+" and aliasid "+aliasDTO.getAliasid());
		return subService.assignSequencer(id,aliasDTO);
		
	}

}
