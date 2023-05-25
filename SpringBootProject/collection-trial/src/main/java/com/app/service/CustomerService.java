package com.app.service;

import java.util.List;

import com.app.dto.BillDTO;
import com.app.dto.ExtraDTO;
import com.app.dto.MessageDTO;
import com.app.dto.UnbilledDTO;
import com.app.dto.WithdrawDTO;

public interface CustomerService {
	List<BillDTO> getBillHistory(Long id);
	UnbilledDTO getUnbilled(Long id);
	List<WithdrawDTO> getLeavesForId(long id);
	List<ExtraDTO> getExtrasForId(long id);
	ExtraDTO postExtr(long id, ExtraDTO extra);
	WithdrawDTO postLeave(long id, WithdrawDTO withdrawDTO);
	List<WithdrawDTO> getInvalidLeaves();
	MessageDTO approveLeave(Long id);
}
