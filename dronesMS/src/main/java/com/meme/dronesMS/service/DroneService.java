package com.meme.dronesMS.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.meme.dronesMS.model.Drone;
import com.meme.dronesMS.model.Medication;
import com.meme.dronesMS.model.Model;
import com.meme.dronesMS.model.State;
import com.meme.dronesMS.repository.DroneRepository;
import com.meme.dronesMS.repository.MedicationRepository;

@Service
public class DroneService {

	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private MedicationRepository medicationRepository;

	public Drone registerDrone(Model model, short weight) {
		List<Drone> registeredDrones = droneRepository.registerDrone(model, weight);
		Drone registeredDrone = null;
		if (registeredDrones != null && registeredDrones.size() > 1) {
			registeredDrone = registeredDrones.get(0);
			if (registeredDrone != null) {
				registeredDrone.setState(State.LOADING);
				droneRepository.save(registeredDrone);
			}
		}
		return registeredDrone;
	}

	public void loadingDrone(Drone drone, Set<Medication> items) {
		drone.setItems(items);
		for (Medication item : items) {
			item.setDrone(drone);
		}
		medicationRepository.save(items);
		drone.setState(State.LOADED);
		droneRepository.save(drone);

	}

	public List<Medication> dronesItems(String serialNumber) {
		return droneRepository.dronesItems(serialNumber);
	}

	public List<Drone> availableDrones() {
		return droneRepository.availableDrones();
	}

	public Drone getDroneBySerialNumber(String serialNumber) {
		return droneRepository.findBySerialNumber(serialNumber);
	}
	
	public List<Drone> getAllDrones(){
		return droneRepository.getAllDrones();
	}
}
