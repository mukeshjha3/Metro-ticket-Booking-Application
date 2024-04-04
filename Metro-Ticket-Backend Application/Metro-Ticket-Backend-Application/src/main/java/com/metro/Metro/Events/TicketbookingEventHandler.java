package com.metro.Metro.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.metro.Metro.service.MailSender;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TicketbookingEventHandler {

	
	private final MailSender mailSender;
	
	@EventListener
	@Async
	public String ticketBookingEventHandler(TicketBookingEvent event) {
		mailSender.sendEmailOnLogin(null, null);
		return "mail sent"; 
		
	}
}
