package com.metro.Metro.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

	private String sourceStation;
	private String destinationStation;
	
	
}
