package com.Project_amaze.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class PatientHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String medicalCondition; 
	
	private LocalDate sinceWhen; 
	
	@ManyToOne
	private PatientOPD patientOPD;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 
	public String getMedicalCondition() {
		return medicalCondition;
	}

	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}

	public LocalDate getSinceWhen() {
		return sinceWhen;
	}

	public void setSinceWhen(LocalDate sinceWhen) {
		this.sinceWhen = sinceWhen;
	}

	public PatientOPD getPatientOPD() {
		return patientOPD;
	}

	public void setPatientOPD(PatientOPD patientOPD) {
		this.patientOPD = patientOPD;
	}

	
	
}
