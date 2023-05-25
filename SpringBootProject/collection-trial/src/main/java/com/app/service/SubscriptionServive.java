package com.app.service;

import java.util.List;

import com.app.dto.AliasDTO;
import com.app.dto.BillDTO;
import com.app.dto.MessageDTO;
import com.app.dto.SubscriptionDTO;
import com.app.dto.ToggleSequenceDTO;
import com.app.entities.Subscription;

public interface SubscriptionServive {
	Subscription createSubscription (Subscription sub);
	List<SubscriptionDTO> getAll();
	SubscriptionDTO getsingleSubscription(long id);
	ToggleSequenceDTO swapSequensers(long id, ToggleSequenceDTO updateInfo);
	List<SubscriptionDTO> getListForRearrange(long id);
	List<SubscriptionDTO> getCreatedCust();
	List<SubscriptionDTO> getSubsForDeliveryAgent(long id);
	MessageDTO assignSequencer(long id, AliasDTO aliasDTO);
}
