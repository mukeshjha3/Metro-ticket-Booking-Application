package com.metro.Metro.service;

import com.metro.Metro.model.Station;

public interface StationService {

	Station saveStation(Station station);
	Double calculateFareBasedOnDistance(String sourceStation, String destinationStation);
}
