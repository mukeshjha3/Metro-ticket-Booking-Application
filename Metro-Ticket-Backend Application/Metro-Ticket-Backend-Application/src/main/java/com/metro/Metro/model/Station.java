package com.metro.Metro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

	@Id
	private String stationId;
	
	private String stationName;
	
	private double distance;
}
