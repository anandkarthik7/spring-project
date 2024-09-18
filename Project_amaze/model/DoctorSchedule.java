package com.Project_amaze.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.Project_amaze.enums.Day;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DoctorSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Doctor doctor; 
	
	private LocalDate date; 
	
	@Enumerated(EnumType.STRING)
	private Day day; 
	
	private LocalTime fromTime; 
	
	private LocalTime toTime; 
	
	private int numberOfAppt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	 

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}

	public int getNumberOfAppt() {
		return numberOfAppt;
	}

	public void setNumberOfAppt(int numberOfAppt) {
		this.numberOfAppt = numberOfAppt;
	} 
	
	
}