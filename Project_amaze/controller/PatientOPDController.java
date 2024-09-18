package com.Project_amaze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project_amaze.exception.AppointmentUnavailableException;
import com.Project_amaze.exception.InvalidIdException;
import com.Project_amaze.model.Medication;
import com.Project_amaze.model.PatientDoctor;
import com.Project_amaze.model.PatientHistory;
import com.Project_amaze.model.PatientOPD;
import com.Project_amaze.service.PatientOPDService;

@RestController
public class PatientOPDController {

	@Autowired
	private PatientOPDService patientOPDService;
	
	@PostMapping("/patient-opd/add")
	public PatientOPD addPatientt(@RequestBody PatientOPD patientOPD) {
		return patientOPDService.addPatientt(patientOPD);
	}
	
	@PostMapping("/patient-opd/history/add/{pid}")
	public ResponseEntity<?> addPatientHistory(@PathVariable int pid, @RequestBody PatientHistory patientHistory) {
		try {
			patientHistory =  patientOPDService.addPatientHistory(pid,patientHistory);
			return ResponseEntity.ok(patientHistory); 
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@PostMapping("/patient-opd/history/medicine-info/add/{historyId}")
	public ResponseEntity<?> addPatientMedication(@PathVariable int historyId, @RequestBody Medication medication) {
		try {
			medication =patientOPDService.addPatientMedication(historyId, medication);	
			return ResponseEntity.ok(medication); 
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@PostMapping("/book-appointment/{patientId}/{doctorId}")
	public ResponseEntity<?> bookAppointment(@PathVariable int patientId, @PathVariable int doctorId,  @RequestBody PatientDoctor patientDoctor ) {
		try {
			patientDoctor =  patientOPDService.bookAppointment(patientId, doctorId, patientDoctor);
			return ResponseEntity.ok(patientDoctor); 
		} catch (AppointmentUnavailableException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		} 
	}
	@GetMapping("/appointments/patient/{patientId}")
    public ResponseEntity<?> getAppointmentsByPatient(@PathVariable int patientId) {
        try {
            List<PatientDoctor> appointments = patientOPDService.findAppointmentsByPatient(patientId);
            return ResponseEntity.ok(appointments);
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAppointmentsByDoctor(@PathVariable int doctorId) {
        try {
            List<PatientDoctor> appointments = patientOPDService.findAppointmentsByDoctor(doctorId);
            return ResponseEntity.ok(appointments);
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}