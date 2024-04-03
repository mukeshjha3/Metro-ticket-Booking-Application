package com.metro.Metro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

	@Id
	private String ticketId;
	
	private String sourceStation;
	
	private String destinationStation;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
}
