package com.Project_amaze.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String patientName;
    private LocalDate admissionDate;
    private String reasonForAdmission;
	public Admission(int id, String patientName, LocalDate admissionDate, String reasonForAdmission) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.admissionDate = admissionDate;
		this.reasonForAdmission = reasonForAdmission;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getReasonForAdmission() {
		return reasonForAdmission;
	}
	public void setReasonForAdmission(String reasonForAdmission) {
		this.reasonForAdmission = reasonForAdmission;
	}

   
}
