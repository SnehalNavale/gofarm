package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Address;
import com.app.entities.Customer;
import com.app.entities.Role;
import com.app.entities.SubStatus;
import com.app.entities.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewSubscriptionDTO {
	//User
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	//Address
	private String line1;
	private String society;
	private String wing;
	private String apartmentNo;
	private String locality;
	private String city;
	private int pinCode;
	
	//Customer
	private String alias;
	private LocalDate StartDate;
	
	//sub
	private double ratePerPacket;
	private int packetPerDay;
	
	/*
	   const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [line1, setLine1] = useState('');
  const [society, setSociety] = useState('');
  const [wing, setWing] = useState('');
  const [apartmentNo, setApartmentNo] = useState('');
  const [locality, setLocality] = useState('');
  const [city, setCity] = useState('');
  const [pincode, setPincode] = useState('');
  const [alias, setAlias] = useState('');
  const [rateperpacket, setRatePerPacket] = useState('');
  const [startDate, setStartDate] = useState('');
	 */
	public Subscription getSub() {
		Address add= new Address(line1, society, wing, apartmentNo, locality, city, pinCode);
		Customer cust = new Customer(firstName, lastName, email, password, Role.CUSTOMER, LocalDate.now(), null);
		Subscription sub= new Subscription(cust, alias, StartDate, null, add, SubStatus.Created, 0, ratePerPacket,packetPerDay, null, null, null, null, -1l);
		return sub;
	}
}
