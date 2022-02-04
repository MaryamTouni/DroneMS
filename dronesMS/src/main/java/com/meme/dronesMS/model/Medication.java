package com.meme.dronesMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "Medication")
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Name is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9-_]*$")  	// name (allowed only letters, numbers, ‘-‘, ‘_’);
	private String name; 
	@Max(500)
	@Min(0)
	private int weight;
	@Pattern(regexp = "^[A-Z0-9_]*$")	    // code (allowed only upper case letters, underscore and numbers);
	private String code; 
	@Lob
	private byte[] image;
	@ManyToOne
	@JoinColumn(name="drone_id" , nullable=false)
	private Drone drone;
	
	public Medication() {

	}

	public Medication(Integer id, String name, int weight, String code, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}
	
}
