package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Subscription extends BaseEntity implements Comparable<Subscription> {
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	private String alias;
	private LocalDate StartDate;
	private LocalDate lastBilledOn;
	@Embedded
	private Address address;
	@Enumerated(EnumType.STRING)
	private SubStatus subStatus;
	private int packetsDelivered;
	private double ratePerPacket;
	private int packetPerDay;
	@OneToMany(mappedBy = "subscription",cascade = CascadeType.ALL, orphanRemoval = true/* ,fetch = FetchType.EAGER */)
	private List<Withdraw> withdraws=new ArrayList<>();
	
	@OneToMany(mappedBy = "subscription",cascade = CascadeType.ALL, orphanRemoval = true/* ,fetch = FetchType.EAGER */)
	private List<Extra> extras=new ArrayList<>();
	
	@OneToMany(mappedBy = "subscription",cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER )
	private List<Bill> bills=new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="line_id")
	private Line line;
	private Long sequencer;
	
	@Override
	public String toString() {
		return "Id: "+this.getId()+", Alias: "+this.getAlias()+", Sequencer: "+this.getSequencer();
	}
	@Override
	public int compareTo(Subscription o) {
		return this.sequencer.compareTo(o.sequencer);
	}
	
	//Helper methods for leaves
	public void addLeave(Withdraw withdraw) {
		withdraws.add(withdraw);
		withdraw.setSubscription(this);
	}
	public void removeLeave(Withdraw withdraw) {
		withdraws.remove(withdraw);
		withdraw.setSubscription(null);
	}
	
	//Helper Methods for Extras
	public void addExtra(Extra extra) {
		extras.add(extra);
		extra.setSubscription(this);
	}
	public void removeExtra(Extra extra) {
		extras.remove(extra);
		extra.setSubscription(null);
	}
	
	//Helper Methods for bills
	public void addBill(Bill bill) {
		bills.add(bill);
		bill.setSubscription(this);
	}
	public void removeBill(Bill bill) {
		bills.remove(bill);
		bill.setSubscription(null);
	}
}
