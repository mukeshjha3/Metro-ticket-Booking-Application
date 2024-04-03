package com.metro.Metro.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.metro.Metro.exception.StationException;
import com.metro.Metro.model.Ticket;
import com.metro.Metro.model.User;
import com.metro.Metro.payload.TicketRequest;
import com.metro.Metro.service.StationService;
import com.metro.Metro.service.TicketService;
import com.metro.Metro.service.UserService;
import com.metro.Metro.utils.JwtUtils;

@RestController
@RequestMapping("/api/user")
//@Secured({"ROLE_USER"})
public class TicketController {

	@Autowired
	public final TicketService ticketService;
	
	@Autowired
	public final StationService stationService;
	
	@Autowired
	public final JwtUtils jwtUtils;
	
	@Autowired
	public final UserService userService;
	
	public TicketController(TicketService ticketService, StationService stationService, JwtUtils jwtUtils,
			UserService userService) {
		super();
		this.ticketService = ticketService;
		this.stationService = stationService;
		this.jwtUtils = jwtUtils;
		this.userService = userService;
	}


	@PostMapping("/book_ticket")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Ticket bookTicket(@RequestBody TicketRequest ticketRequest,@RequestHeader ("Authorization") String jwtToken) {
		Ticket ticket = ticketService.ticketRequestToTicketMapping(ticketRequest);
		String userEmail = jwtUtils.extractEmailfromToken(jwtToken);
		User user = userService.getUser(userEmail);
		ticket.setUser(user);
		Ticket bookedticket = ticketService.bookticket(ticket);
		return bookedticket;
	}
	
	@PostMapping("/ticket_fair")
	@ResponseStatus(code = HttpStatus.OK)
	private double calculateFair(@RequestBody TicketRequest ticketRequest) {
		String sourceStation = ticketRequest.getSourceStation();
		String destinationStation = ticketRequest.getDestinationStation();
		if (sourceStation.equals(destinationStation)) {
			throw new StationException("Source staion and destination Station cannot be same...");
		}
		Double fare = stationService.calculateFareBasedOnDistance(sourceStation, destinationStation);
		
		return fare;
	}
	
	@GetMapping("/mytickets")
	@ResponseStatus(code = HttpStatus.OK)
	private List<Ticket> getAllTicketOfUser(@RequestHeader("Authorization") String jwtToken){
		List<Ticket> allTicketOfUser = ticketService.getAllTicketOfUser(jwtToken);
		return allTicketOfUser;
	}

	
}
