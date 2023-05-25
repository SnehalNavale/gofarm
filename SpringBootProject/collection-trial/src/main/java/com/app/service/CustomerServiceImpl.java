package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.BillDTO;
import com.app.dto.ExtraDTO;
import com.app.dto.MessageDTO;
import com.app.dto.UnbilledDTO;
import com.app.dto.WithdrawDTO;
import com.app.entities.Bill;
import com.app.entities.Customer;
import com.app.entities.Extra;
import com.app.entities.Withdraw;
import com.app.repository.CustomerRepo;
import com.app.repository.ExtraRepo;
import com.app.repository.WithdrawRepo;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired 
	private ExtraRepo extraRepo;
	
	@Autowired
	private WithdrawRepo withdrawRepo;

	@Override
	public List<BillDTO> getBillHistory(Long id) {
		Customer cust=custRepo.findById(id).orElseThrow();
		List<BillDTO> billDTOs=new ArrayList<>();
		List<Bill> bills= cust.getSubscription().getBills();
		bills.forEach(b->billDTOs.add(new BillDTO(b)));
		return billDTOs;
	}

	@Override
	public UnbilledDTO getUnbilled(Long id) {
		Customer cust=custRepo.findById(id).orElseThrow();
		return new UnbilledDTO(cust);
	}

	@Override
	public List<WithdrawDTO> getLeavesForId(long id) {
		Customer customer= custRepo.findById(id).orElseThrow();
		List<Withdraw> withdraws= customer.getSubscription().getWithdraws();
		List<Withdraw> filteredWithdraws = withdraws.stream().filter(w->w.isFulfilled()==false).collect(Collectors.toList());
		List<WithdrawDTO> withDrawDTOs=new ArrayList<>();
		filteredWithdraws.forEach(w->withDrawDTOs.add(new WithdrawDTO(w)));
		return withDrawDTOs;
		 
	}

	@Override
	public List<ExtraDTO> getExtrasForId(long id) {
		Customer customer= custRepo.findById(id).orElseThrow();
		List<Extra> extras = customer.getSubscription().getExtras();
		List<Extra> filteredExtras = extras.stream().filter(c->c.isDelivered()==false).collect(Collectors.toList());
		List<ExtraDTO> extraDTOs= new ArrayList<>();
		filteredExtras.stream().forEach(e->extraDTOs.add(new ExtraDTO(e)));
		return extraDTOs;
		
	}

	@Override
	public ExtraDTO postExtr(long id, ExtraDTO dto) {
		Customer customer= custRepo.findById(id).orElseThrow();
		dto.setDelivered(false);
		Extra extra= new Extra(dto.getExtraDate(), dto.isDelivered(), dto.getPacketsCount(), customer.getSubscription());
		Extra persistedExtra= extraRepo.save(extra);
		return new ExtraDTO(persistedExtra);
	}

	@Override
	public WithdrawDTO postLeave(long id, WithdrawDTO withdrawDTO) {
		Customer customer= custRepo.findById(id).orElseThrow();

		Withdraw withdraw= new Withdraw(withdrawDTO.getLeaveDate(), false, false, customer.getSubscription());
		Withdraw persistedWithdraw=withdrawRepo.save(withdraw);
		return new WithdrawDTO(persistedWithdraw);
	}

	@Override
	public List<WithdrawDTO> getInvalidLeaves() {
		List<Withdraw> leaves= withdrawRepo.findByIsValid(false);
		System.out.println("Total invalid leaves are "+leaves.size());
		List<WithdrawDTO> toReturn=new ArrayList<>();
		leaves.forEach(w->toReturn.add(new WithdrawDTO(w)));
		return toReturn;
	}

	@Override
	public MessageDTO approveLeave(Long id) {
		Withdraw withdraw = withdrawRepo.findById(id).orElseThrow();
		withdraw.setValid(true);
		System.out.println("Leave is validated successfully");
		return new MessageDTO("Leave approved Sussessfully");
	}

}
