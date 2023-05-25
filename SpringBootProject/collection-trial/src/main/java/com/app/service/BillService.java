package com.app.service;

import java.util.List;

import com.app.dto.BillDTO;
import com.app.dto.MessageDTO;

public interface BillService {

	List<BillDTO> getAllForAgent(long id);

	boolean markPaid(long id);

	List<BillDTO> getAllBills();

	MessageDTO generateAllBills();

}
