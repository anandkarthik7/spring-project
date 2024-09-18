package com.Project_amaze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project_amaze.exception.InvalidIdException;
import com.Project_amaze.model.Doctor;
import com.Project_amaze.model.DoctorSchedule;
import com.Project_amaze.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/doctor/add")
	public Doctor addDoctor(@RequestBody Doctor doctor) {
		return doctorService.addDcotor(doctor);
	}
	
	@PostMapping("/doctor/schedule/add/{doctorId}")
	public ResponseEntity<?> addDoctorSchedule(@PathVariable int doctorId, @RequestBody DoctorSchedule doctorSchedule) {
		try {
			
			DoctorSchedule ds=doctorService.addDoctorSchedule(doctorId,doctorSchedule);
			return ResponseEntity.ok(ds);
			
		} catch (InvalidIdException e) {
			 return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
}
