package com.meme.dronesMS.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meme.dronesMS.model.Drone;
import com.meme.dronesMS.model.Medication;
import com.meme.dronesMS.model.Model;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Integer>{

	
	@Query("SELECT d FROM Drone d WHERE d.batteryCapacity >= 25 and (d.state = 0 or d.state = 4)and d.model = ?1 and d.weight > ?2 ")
	public List<Drone> registerDrone(Model model , short weight);
	
	@Query("SELECT m FROM Medication m WHERE m.drone.serialNumber = ?1")
	public List<Medication> dronesItems(String serialNumber);
	
	@Query("SELECT d FROM Drone d WHERE d.batteryCapacity >= 25 and (d.state = 0 or d.state = 4)")
	public List<Drone> availableDrones();
	
	@Query("SELECT d.batteryCapacity FROM Drone d WHERE d.serialNumber = ?1")
	public short getBatteryLevel(String serialNumber);
	
	@Query("SELECT d FROM Drone d")
	public List<Drone> getAllDrones();
	
	public Drone findBySerialNumber(String serialNumber);
}
