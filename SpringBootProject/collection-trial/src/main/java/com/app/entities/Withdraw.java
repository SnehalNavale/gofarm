package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Withdraw extends BaseEntity {
	private LocalDate leaveDate;
	private boolean isValid;
	private boolean isFulfilled;
	@JsonIgnoreProperties
	@ManyToOne
	private Subscription subscription;
}
