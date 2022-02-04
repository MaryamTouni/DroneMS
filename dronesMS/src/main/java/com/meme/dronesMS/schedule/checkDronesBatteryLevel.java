package com.meme.dronesMS.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meme.dronesMS.model.Drone;
import com.meme.dronesMS.service.DroneService;

@Component
public class checkDronesBatteryLevel {

	private static final Logger log = LoggerFactory.getLogger(checkDronesBatteryLevel.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private DroneService droneService;
	
	@Scheduled(fixedRate=300000)  //Run every 5 minutes (5*60*1000)..
	public void reportBatteryLevel() {
		List<Drone> drones = droneService.getAllDrones();
		if(drones != null && drones.size()>1){
			for(Drone d: drones){
				log.info("The drone which serialNumber is {} its battery capacity is {} at time {}",d.getSerialNumber() , d.getBatteryCapacity(),dateFormat.format(new Date()));
			}
		}
		
	}
}
