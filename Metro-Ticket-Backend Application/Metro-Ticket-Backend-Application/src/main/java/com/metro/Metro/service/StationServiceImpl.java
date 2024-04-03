package com.metro.Metro.service;
import org.springframework.stereotype.Service;

import com.metro.Metro.model.Station;
import com.metro.Metro.repository.StationRepo;


@Service
public class StationServiceImpl implements StationService{

	private final StationRepo stationRepo;
	
	
	public StationServiceImpl(StationRepo stationRepo){
		this.stationRepo=stationRepo;
	}
	
	
	@Override
	public Station saveStation(Station station) {
		Station savedStation = stationRepo.save(station);
		return savedStation;
	}


	@Override
	public Double calculateFareBasedOnDistance(String sourceStation, String destinationStation) {
		double fare;
		double distance;
		Station startingStation = stationRepo.findByStationName(sourceStation);
		Station destination = stationRepo.findByStationName(destinationStation);
		
		if (startingStation.getDistance() >  destination.getDistance()) {
			 distance = startingStation.getDistance() - destination.getDistance();
		}
		else {
			 distance =  destination.getDistance() - startingStation.getDistance();
		}
		if(distance < 5.00 ) {
			fare=5.00;
		}
		else if (distance <10.00  && distance >5.00) {
		fare = 10.00 ;
		}
		else if (distance <15.00  && distance >10.00) {
			fare = 15.00 ;
			}
		else {
			fare =20.00;
		}
		return fare;
	}

	
}
