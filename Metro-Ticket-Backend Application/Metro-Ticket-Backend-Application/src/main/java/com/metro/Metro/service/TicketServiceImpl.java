package com.metro.Metro.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.metro.Metro.model.Ticket;
import com.metro.Metro.model.User;
import com.metro.Metro.payload.TicketRequest;
import com.metro.Metro.repository.TicketRepo;
import com.metro.Metro.repository.UserRepo;
import com.metro.Metro.utils.JwtUtils;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public Ticket bookticket(Ticket ticket) {
		Ticket saveTicket = ticketRepo.save(ticket);
		return saveTicket;
	}

	public Ticket ticketRequestToTicketMapping(TicketRequest request) {
		Ticket ticket = Ticket.builder()
				.destinationStation(request.getDestinationStation())
				.sourceStation(request.getSourceStation())
				.ticketId(UUID.randomUUID().toString())
				.build();
		return ticket;
		
	}

	@Override
	public List<Ticket> getAllTicketOfUser(String token) {
	String userEmail = jwtUtils.extractEmailfromToken(token);
	User user = userRepo.findByEmailId(userEmail);
	String userId = user.getUserId();
	List<Ticket> allTicket = ticketRepo.findByUserId(userId);
	return allTicket;
	}
}
