package com.metro.Metro.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.metro.Metro.model.Station;
import com.metro.Metro.service.StationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin")
//@Secured({"ROLE_ADMIN",})
public class StationController {

	
	private final StationService stationService;
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/save_station")
	public Station saveStation(@RequestBody Station station) {
		station.setStationId(UUID.randomUUID().toString());
		return stationService.saveStation(station);
	}
	
}
