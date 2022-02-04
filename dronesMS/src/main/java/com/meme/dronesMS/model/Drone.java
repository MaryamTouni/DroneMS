package com.meme.dronesMS.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "Drone")
public class Drone {
	@Id
	private Integer id;
	@NotBlank(message = "Serial number is mandatory")
	@Size(max=100)
	private String serialNumber; // serial number (100 characters max);
	private Model model;
	@Max(500)
	private short weight; // weight limit (500gr max);
	@Min(0)
	@Max(100)
	private short batteryCapacity;
	private State state;
	@OneToMany
	private Set<Medication> items;
	
	public Drone() {

	}

	public Drone(Integer id, String serialNumber, Model model, short weight, short batteryCapacity, State state) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.model = model;
		this.weight = weight;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public short getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public short getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(short batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Set<Medication> getItems() {
		return items;
	}

	public void setItems(Set<Medication> items) {
		this.items = items;
	}
	
}
