package com.app.service;

import java.util.List;

import com.app.dto.DeliveryDTO;
import com.app.dto.LineDTO;
import com.app.dto.LineOrderDTO;
import com.app.dto.MessageDTO;
import com.app.entities.DeliveryStatus;


public interface LineOrderService {
	List<LineOrderDTO> getAllCreated();

	String createOrders();

	List<DeliveryDTO> getAllDeliveries(Long id);

	LineOrderDTO getSingleLineOrder(Long id);

	DeliveryStatus updateDeliveryStatus(Long id);

	String activateOrder(Long id);

	List<LineDTO> fetchLines();

	MessageDTO assignLine(Long id, String lineName);

}
