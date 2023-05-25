package com.app.dto;

import java.time.LocalDate;

import com.app.entities.SubStatus;
import com.app.entities.Subscription;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubscriptionDTO {

	/*
	 <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>line1</th>
            <th>Society</th>
            <th>Wing</th>
            <th>ApartmentNo</th>
            <th>Locality</th>
            <th>City</th>
            <th>Pincode</th>
            <th>Alias</th>
            <th>Rateperpacket</th>
            <th>StartDate</th>
            <th>LastBilledOn</th>
            <th>subStatus</th>
            <th>PacketDelivered</th>
            <th>Extras</th>
            <th>LineId</th>
            <th>Sequencer</th>

	 */
	
	private Long id;
	//User
	private String firstName;
	private String lastName;
	private String email;
	
	//Address
	private String line1;
	private String society;
	private String wing;
	private String apartmentNo;
	private String locality;
	private String city;
	private int pinCode;
	
	private String alias;
	private double ratePerPacket;
	private LocalDate StartDate;
	private LocalDate lastBilledOn;
	private SubStatus subStatus;
	private int packetsDelivered;
	private Long lineId;
	private Long sequencer;
	private int packetPerDay;
	
	//sub
	public SubscriptionDTO(Subscription sub) {
		id=sub.getId();
		firstName=sub.getCustomer().getFirstName();
		lastName=sub.getCustomer().getLastName();
		email=sub.getCustomer().getEmail();
		line1=sub.getAddress().getLine1();
		society=sub.getAddress().getSociety();
		wing=sub.getAddress().getWing();
		apartmentNo=sub.getAddress().getApartmentNo();
		locality=sub.getAddress().getLocality();
		city=sub.getAddress().getCity();
		pinCode=sub.getAddress().getPinCode();
		alias=sub.getAlias();
		ratePerPacket=sub.getRatePerPacket();
		StartDate=sub.getStartDate();
		lastBilledOn=sub.getLastBilledOn();
		subStatus=sub.getSubStatus();
		packetsDelivered=sub.getPacketsDelivered();
		packetPerDay=sub.getPacketPerDay();
		if(sub.getLine()==null)
			lineId=-1l;
		else
			lineId=sub.getLine().getId();
		sequencer=sub.getSequencer();
		
	}

}
