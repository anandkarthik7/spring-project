package com.Project_amaze.model;

import com.Project_amaze.enums.DoctorSpecialization;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name; 
	
	@Enumerated(EnumType.STRING)
	private DoctorSpecialization doctorSpecialization;
	
	@OneToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DoctorSpecialization getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(DoctorSpecialization doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	} 
	
	
}