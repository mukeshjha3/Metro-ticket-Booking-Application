package com.metro.Metro.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metro.Metro.model.Station;


@Repository
public interface StationRepo extends JpaRepository<Station, String> {

	Station findByStationName(String stationName);
}

