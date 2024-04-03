package com.metro.Metro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.metro.Metro.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, String>{

	@Query(nativeQuery = true , value = "select * from Ticket where user_id = :userId")
	List<Ticket>findByUserId(String userId);
}
