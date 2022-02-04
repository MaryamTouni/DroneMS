package com.meme.dronesMS.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meme.dronesMS.exception.DroneNotAvailableException;
import com.meme.dronesMS.exception.DroneNotfoundException;
import com.meme.dronesMS.model.Drone;
import com.meme.dronesMS.model.Medication;
import com.meme.dronesMS.model.Model;
import com.meme.dronesMS.service.DroneService;

@RestController
@RequestMapping("/drone")
@Validated
public class DroneController {

	@Autowired
	private DroneService droneService;
	
	@PutMapping("/registeringDrone")
	public ResponseEntity<String> registeringDrone(@RequestParam("model") Model model ,@Valid @RequestParam("weight") @Max(500) short weight){
		Drone registeredDrone = droneService.registerDrone(model , weight);
		if(registeredDrone==null){
			throw new DroneNotAvailableException();
		}else{
			return new ResponseEntity<String>("You have been registered a drone Successfully! Drone's serial number is: "+registeredDrone.getSerialNumber(), HttpStatus.OK);
		}
	}
	
	@PutMapping("/loadingDrone")
	public ResponseEntity<String> loadingDroneWithMedication(@RequestParam("serialNumber") @Size(max=100) @NotBlank String serialNumber , @RequestBody Set<Medication> items){
		Drone drone = droneService.getDroneBySerialNumber(serialNumber);
		if(drone==null){
			throw new DroneNotfoundException();
		}else{
			droneService.loadingDrone(drone , items);
			return new ResponseEntity<String>("Drone has been loaded with medication items", HttpStatus.OK);
		}
	}
	
	@GetMapping("/dronesItems")
	public List<Medication> itemsOnDrones(@RequestParam("serialNumber")@Size(max=100) String serialNumber){
		Drone drone = droneService.getDroneBySerialNumber(serialNumber);
		if(drone==null){
			throw new DroneNotfoundException();
		}else{
			return droneService.dronesItems(serialNumber);
		}
	}
	
	@GetMapping("/availableDrones")
	public List<Drone> availableDrones(){
		List<Drone> drones = droneService.availableDrones();
		if(drones==null || drones.size()<1){
			throw new DroneNotfoundException();
		}else{
			return drones;
		}
	
	
	}
	
	@GetMapping("/checkBatteryLevel")
	public short checkBatteryLevel(@RequestParam("serialNumber")@NotBlank @Size(max=100) String serialNumber){
		Drone drone = droneService.getDroneBySerialNumber(serialNumber);
		if(drone==null){
			throw new DroneNotfoundException();
		}else{
			return drone.getBatteryCapacity();
		}
	}

}
