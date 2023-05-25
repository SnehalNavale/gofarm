package com.app.dto;

import com.app.entities.Line;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineDTO {
	private Long id;
	private String lineName;
	public LineDTO(Line line){
		id=line.getId();
		lineName=line.getLineName();
	}
}
