package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.AliasDTO;
import com.app.dto.MessageDTO;
import com.app.dto.SubscriptionDTO;
import com.app.dto.ToggleSequenceDTO;
import com.app.entities.Customer;
import com.app.entities.DeliveryBoy;
import com.app.entities.SubStatus;
import com.app.entities.Subscription;
import com.app.repository.CustomerRepo;
import com.app.repository.DeliveryBoyRepo;
import com.app.repository.SubscriptionRepo;

@Service
@Transactional
public class SubscriptionServiveImpl implements SubscriptionServive {
	
	@Autowired
	private SubscriptionRepo subRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	DeliveryBoyRepo delBoyRepo;

	@Override
	public Subscription createSubscription(Subscription sub) {
		return subRepo.save(sub);
	}

	@Override
	public List<SubscriptionDTO> getAll() {
		List<Subscription> list = subRepo.findAll();
		list.forEach(e->System.out.println(e.getId()+", "+e.getAlias()));
		List<SubscriptionDTO> toSend=new ArrayList<>();
		list.stream().forEach((s)->toSend.add(new SubscriptionDTO(s)));
		toSend.forEach(System.out::println);
		return toSend;
	}

	@Override
	public SubscriptionDTO getsingleSubscription(long id) {
		Customer customer= customerRepo.findById(id).orElseThrow();
		
		return new SubscriptionDTO(customer.getSubscription());
	}

	@Override
	public ToggleSequenceDTO swapSequensers(long id, ToggleSequenceDTO updateInfo) {
		DeliveryBoy delBoy = delBoyRepo.findById(id).orElseThrow();
		List<Subscription> subscriptions= delBoy.getLine().getList();
		subscriptions.stream().filter(s->s.getId()==updateInfo.getId1()).forEach(s->s.setSequencer(updateInfo.getSeq1()));
		subscriptions.stream().filter(s->s.getId()==updateInfo.getId2()).forEach(s->s.setSequencer(updateInfo.getSeq2()));
		
		Long sq1= subscriptions.stream().filter(s->s.getId()==updateInfo.getId1()).findFirst().orElseThrow().getSequencer();
		Long sq2= subscriptions.stream().filter(s->s.getId()==updateInfo.getId2()).findFirst().orElseThrow().getSequencer();
		
		ToggleSequenceDTO toReturn= new ToggleSequenceDTO(updateInfo.getId1(), sq1, updateInfo.getId2(), sq2);
		
		return toReturn;
	}

	@Override
	public List<SubscriptionDTO> getListForRearrange(long id) {
		DeliveryBoy delBoy= delBoyRepo.findById(id).orElseThrow();
		List<Subscription> subscriptions= delBoy.getLine().getList().stream().filter(s->!s.getSequencer().equals(-1l)).sorted().collect(Collectors.toList());
		List<SubscriptionDTO> subscriptionDTOs=new ArrayList<>();
		subscriptions.forEach(s->subscriptionDTOs.add(new SubscriptionDTO(s)));
		return subscriptionDTOs;
	}

	@Override
	public List<SubscriptionDTO> getCreatedCust() {
		List<Subscription> subs= subRepo.findBySubStatus(SubStatus.Created).stream().filter(s->s.getLine()==null).collect(Collectors.toList());
		List<SubscriptionDTO> toReturn = new ArrayList<>();
		subs.forEach(s->toReturn.add(new SubscriptionDTO(s)));
		return toReturn;
	}

	@Override
	public List<SubscriptionDTO> getSubsForDeliveryAgent(long id) {
		DeliveryBoy delBoy= delBoyRepo.findById(id).orElseThrow();
		List<Subscription> subscriptions= delBoy.getLine().getList().stream().filter(s->s.getLine()!=null).filter(s->s.getSequencer().equals(-1l)).collect(Collectors.toList());
		List<SubscriptionDTO> subscriptionDTOs=new ArrayList<>();
		subscriptions.forEach(s->subscriptionDTOs.add(new SubscriptionDTO(s)));
		return subscriptionDTOs;
	}

	@Override
	public MessageDTO assignSequencer(long id, AliasDTO aliasDTO) {
		DeliveryBoy delBoy= delBoyRepo.findById(id).orElseThrow();
		Long seq= Long.parseLong(aliasDTO.getAliasid());
		List<Subscription> subscriptions= delBoy.getLine().getList().stream().filter(s->!s.getSequencer().equals(-1l)).sorted().filter(s->s.getSequencer().compareTo(seq)>0).collect(Collectors.toList());
		Subscription target= subRepo.findById(aliasDTO.getSubid()).orElseThrow();
		target.setSequencer(seq+1);
		target.setSubStatus(SubStatus.Active);
		subscriptions.forEach((s)->{
			s.setSequencer(s.getSequencer()+1);
		});	
		return new MessageDTO("Sequencer assigned successfully");
	}

}
