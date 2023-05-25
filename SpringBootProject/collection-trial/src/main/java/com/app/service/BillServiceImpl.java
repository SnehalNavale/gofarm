package com.app.service;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.BillDTO;
import com.app.dto.MessageDTO;
import com.app.entities.Bill;
import com.app.entities.DeliveryBoy;
import com.app.entities.Extra;
import com.app.entities.SubStatus;
import com.app.entities.Subscription;
import com.app.repository.BillRepo;
import com.app.repository.DeliveryBoyRepo;
import com.app.repository.SubscriptionRepo;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepo billRepo;
	
	@Autowired
	private DeliveryBoyRepo deliveryBoyRepo;
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;

	@Override
	public List<BillDTO> getAllForAgent(long id) {
		DeliveryBoy deliveryBoy=deliveryBoyRepo.findById(id).orElseThrow();
		List<Subscription> subs= deliveryBoy.getLine().getList();
		List<Bill> unfiltered = new ArrayList<>();
		subs.forEach(sub->unfiltered.addAll(billRepo.findBySubscription(sub)));
		List<Bill> filtered= unfiltered.stream().filter(bill->bill.getStartDate().compareTo(LocalDate.now().minusMonths(10))>0).collect(Collectors.toList());
		List<BillDTO> toSend= new ArrayList<>(); 
		filtered.forEach(b->toSend.add(new BillDTO(b)));
		return toSend;
	}

	@Override
	public boolean markPaid(long id) {
		Bill bill= billRepo.findById(id).orElseThrow();
		if(!bill.isPaid()) {
			bill.setPaid(true);	
			bill.setPaidOn(LocalDate.now());
		}
		return bill.isPaid();
	}

	@Override
	public List<BillDTO> getAllBills() {
		List<Bill> bills = billRepo.findAll();
		List<BillDTO> toSend= new ArrayList<>(); 
		bills.forEach(b->toSend.add(new BillDTO(b)));
		return toSend;

	}

	@Override
	public MessageDTO generateAllBills() {
		List<Subscription> subscriptions= subscriptionRepo.findBySubStatus(SubStatus.Active);
		List<Bill> bills=new ArrayList<>();
		subscriptions.stream().forEach(s->{
				List<Extra> extras=s.getExtras().stream().filter(e->e.getExtraDate().isAfter(e.getSubscription().getLastBilledOn())).filter(e->e.isDelivered()).collect(Collectors.toList());
				int packetCount=0;
				for (Extra extra : extras) {
					packetCount+=extra.getPacketsCount();
				}
				int leaves=(int) s.getWithdraws().stream().filter(w->w.getLeaveDate().isAfter(w.getSubscription().getLastBilledOn())).filter(w->w.isFulfilled()).count();
				
				Bill bill=new Bill(s.getLastBilledOn().plusDays(1), LocalDate.now(), leaves, packetCount, s.getRatePerPacket()*s.getPacketsDelivered(), false, null, s);
				bills.add(bill);
		});
		List<Bill> persistedBills=billRepo.saveAll(bills);
		persistedBills.forEach(b->{
			b.getSubscription().setPacketsDelivered(0);
			b.getSubscription().setLastBilledOn(LocalDate.now());
		});
		return new MessageDTO("Bills released successfully");
	}
}
