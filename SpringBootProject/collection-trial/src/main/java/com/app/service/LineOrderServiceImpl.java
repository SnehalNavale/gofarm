package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DeliveryDTO;
import com.app.dto.LineDTO;
import com.app.dto.LineOrderDTO;
import com.app.dto.MessageDTO;
import com.app.entities.Delivery;
import com.app.entities.DeliveryBoy;
import com.app.entities.DeliveryStatus;
import com.app.entities.Extra;
import com.app.entities.Line;
import com.app.entities.LineOrder;
import com.app.entities.Status;
import com.app.entities.SubStatus;
import com.app.entities.Subscription;
import com.app.entities.Withdraw;
import com.app.repository.DeliveryBoyRepo;
import com.app.repository.DeliveryRepo;
import com.app.repository.LineOrderRepo;
import com.app.repository.LineRepo;
import com.app.repository.SubscriptionRepo;

@Service
@Transactional

public class LineOrderServiceImpl implements LineOrderService {

	@Autowired
	private LineOrderRepo lineOrderRepo;

	@Autowired
	private LineRepo lineRepo;
	
	@Autowired
	private DeliveryBoyRepo deliveryBoyRepo;
	
	@Autowired
	private DeliveryRepo deliveryRepo;
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;

	@Override
	public List<LineOrderDTO> getAllCreated() {
		List<LineOrder>  createdOrders = lineOrderRepo.findByStatus(Status.CREATED);
		List<LineOrder>  activeOrders = lineOrderRepo.findByStatus(Status.ACTIVE);
		List<LineOrderDTO> orderDTOs=new ArrayList<>();
		createdOrders.forEach((e)->orderDTOs.add(new LineOrderDTO(e)));
		activeOrders.forEach((e)->orderDTOs.add(new LineOrderDTO(e)));
		return orderDTOs;
	}

	@Override
	public String createOrders() {
		List<Line> lines= lineRepo.findAll();
		for (Line line : lines) {
			LineOrder lineOrder=new LineOrder();
			lineOrder.setLineNo(line);
			lineOrder.setOrderDate(LocalDate.now());
			lineOrder.setStatus(Status.CREATED);
			List<Delivery> deliveries = lineOrder.getDeliveryList();
			List<Subscription> subscriptions=line.getList();
			for (Subscription subscription : subscriptions) {
				if((subscription.getSubStatus()==SubStatus.Active) && subscription.getStartDate().minusDays(1).isBefore(LocalDate.now())) {
					Delivery delivery=new Delivery(subscription, subscription.getPacketPerDay(), DeliveryStatus.NOT_DELIVERED, lineOrder);
					List<Extra> extras= subscription.getExtras();
					int extra= (int)extras.stream().filter(e->e.getExtraDate()==LocalDate.now()).count();
					List<Withdraw> withdraws = subscription.getWithdraws();
					if(withdraws.stream().anyMatch(e->e.getLeaveDate()==LocalDate.now())) {
						delivery.setNoOfPackets(extra);
					}else {
						delivery.setNoOfPackets(extra+delivery.getNoOfPackets());
						deliveries.add(delivery);
					}
				}
			}
			lineOrderRepo.save(lineOrder);
		}
		return "Line Orders created successfully";
	}

	@Override
	public List<DeliveryDTO> getAllDeliveries(Long id) {
		LineOrder order=lineOrderRepo.findById(id).orElseThrow();
		
		List<DeliveryDTO> deliveries= new ArrayList<>();
		order.getDeliveryList().forEach(d->deliveries.add(new DeliveryDTO(d)));
		return deliveries;
	}

	@Override
	public LineOrderDTO getSingleLineOrder(Long id) {
		
		DeliveryBoy agent= deliveryBoyRepo.findById(id).orElseThrow();
		Line line=agent.getLine();

		List<LineOrder> orders= lineOrderRepo.findByLineNo(line);
		LineOrder order= orders.stream().filter(e->e.getStatus()==Status.ACTIVE).findFirst().orElseThrow();
		return new LineOrderDTO(order);
	}

	@Override
	public DeliveryStatus updateDeliveryStatus(Long id) {
		Delivery delivery= deliveryRepo.findById(id).orElseThrow();
		int packets= delivery.getNoOfPackets();
		if(delivery.getStatus()==DeliveryStatus.NOT_DELIVERED) {
			delivery.setStatus(DeliveryStatus.DELIVERED);
			int finalCount = delivery.getSubscrip().getPacketsDelivered()+packets;
			delivery.getSubscrip().setPacketsDelivered(finalCount);
		}
		return delivery.getStatus();
	}

	@Override
	public String activateOrder(Long id) {
		LineOrder order= lineOrderRepo.findById(id).orElseThrow();
		if(order.getStatus()==Status.CREATED) {
			order.setStatus(Status.ACTIVE);
			return "Order activated successfully";
		}else {
			return "Either order is active already or Its status is not created yet";
		}
	}

	@Override
	public List<LineDTO> fetchLines() {
		List<Line> lines= lineRepo.findAll();
		List<LineDTO> dtos=new ArrayList<>();
		lines.forEach(l->dtos.add(new LineDTO(l)));
		return dtos;
	}

	@Override
	public MessageDTO assignLine(Long id, String lineName) {
		Line line=lineRepo.findByLineName(lineName).get(0);
		subscriptionRepo.findById(id).orElseThrow().setLine(line);
		return new MessageDTO("Line assigned to Customer successfully");
	}	
}
