//package com.Project_amaze.model;
//
//import jakarta.persistence.GeneratedValue;
//
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import com.Project_amaze.model.PatientOPD;
//import com.Project_amaze.model.Doctor; 
//
//public class InPatientTest {
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//	
//	@ManyToOne
//	private InPatient inPatient;
//	//patientid
//	
//	//testid
//	@ManyToOne
//	private Test test;
//	
//	//doctorid
//	@ManyToOne
//	private Doctor doctor;
//	
//	
//	private LocalDate Date;