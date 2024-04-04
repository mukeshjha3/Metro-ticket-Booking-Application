package com.metro.Metro.Events;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TicketBookingEvent {
	
	private String email;
	private String userName;
	
	public TicketBookingEvent() {
        super();
    }

	public TicketBookingEvent(String email, String userName) {
		super();
		this.email = email;
		this.userName = userName;
	}
	
	
	
	
}
