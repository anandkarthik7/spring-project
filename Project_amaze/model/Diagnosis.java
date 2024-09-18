package com.Project_amaze.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int admissionId;
    private String symptoms;
    private String diagnosisDetails;

    @ManyToOne
    private Doctor doctor;

    private LocalDate diagnosisDate;

	public Diagnosis(int id, int admissionId, String symptoms, String diagnosisDetails, Doctor doctor,
			LocalDate diagnosisDate) {
		super();
		this.id = id;
		this.admissionId = admissionId;
		this.symptoms = symptoms;
		this.diagnosisDetails = diagnosisDetails;
		this.doctor = doctor;
		this.diagnosisDate = diagnosisDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDiagnosisDetails() {
		return diagnosisDetails;
	}

	public void setDiagnosisDetails(String diagnosisDetails) {
		this.diagnosisDetails = diagnosisDetails;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(LocalDate diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

    // Getters and Setters
    
}
