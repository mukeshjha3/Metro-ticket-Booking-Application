package com.metro.Metro.service;
import java.util.List;
import com.metro.Metro.model.Ticket;
import com.metro.Metro.payload.TicketRequest;

public interface TicketService {

	public Ticket bookticket(Ticket Ticket);
	public Ticket ticketRequestToTicketMapping(TicketRequest request);
	public List<Ticket> getAllTicketOfUser(String token);
	
}
