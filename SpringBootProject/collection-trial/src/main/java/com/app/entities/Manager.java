package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Manager extends User {
	@OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
	private List<Line> lines = new ArrayList<>();
	
	public Manager(String firstName, String lastName, String email, String password, Role role) {
		super(firstName, lastName, email, password, role);
	}	
	public void addLine(Line line) {
		lines.add(line);
		line.setManager(this);
	}
	public void removeLine(Line line) {
		lines.remove(line);
		line.setManager(null);
	}

	
}
