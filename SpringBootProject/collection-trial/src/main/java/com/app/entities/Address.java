package com.app.entities;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Address{
	private String line1;
	private String society;
	private String wing;
	private String apartmentNo;
	private String locality;
	private String city;
	private int pinCode;
}
