package com.Project_amaze.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project_amaze.exception.AppointmentUnavailableException;
import com.Project_amaze.exception.InvalidIdException;
import com.Project_amaze.model.Doctor;
import com.Project_amaze.model.DoctorSchedule;
import com.Project_amaze.model.Medication;
import com.Project_amaze.model.PatientDoctor;
import com.Project_amaze.model.PatientHistory;
import com.Project_amaze.model.PatientOPD;
import com.Project_amaze.model.User;
import com.Project_amaze.repository.DoctorRepository;
import com.Project_amaze.repository.DoctorScheduleRepository;
import com.Project_amaze.repository.MedicationRepository;
import com.Project_amaze.repository.PatientDoctorRepository;
import com.Project_amaze.repository.PatientHistoryRepository;
import com.Project_amaze.repository.PatientOPDRepository;
import com.Project_amaze.repository.UserRepository;

@Service
public class PatientOPDService {

	@Autowired
	private PatientOPDRepository patientOPDRepository;

	@Autowired
	private PatientHistoryRepository patientHistoryRepository;
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	
	@Autowired
	private DoctorScheduleRepository doctorScheduleRepository;
	
	public PatientOPD addPatientt(PatientOPD patientOPD) {
		 User userInfo = patientOPD.getUser();
		 userInfo.setRole("ROLE_PATIENT");
		 userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		 userInfo=  userRepository.save(userInfo);
		 patientOPD.setUser(userInfo);
		
		return patientOPDRepository.save(patientOPD);
	}

	public PatientHistory addPatientHistory(int pid, PatientHistory patientHistory) throws InvalidIdException {
		//fetch PatinetOPD basis pid
		Optional<PatientOPD> optional =  patientOPDRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIdException("Patient ID Invalid"); 
		
		PatientOPD patientOPD = optional.get();
		patientHistory.setPatientOPD(patientOPD);
		return patientHistoryRepository.save(patientHistory);
	}

	public Medication addPatientMedication(int historyId, Medication medication) throws InvalidIdException {
		//fetch PatientHistory basis historyId
		Optional<PatientHistory> optional =  patientHistoryRepository.findById(historyId);
		if(optional.isEmpty())
			throw new InvalidIdException("Patient ID Invalid"); 
		
		PatientHistory patientHistory = optional.get();
		medication.setPatientHistory(patientHistory);
		
		return medicationRepository.save(medication);
		
	}

	public PatientDoctor bookAppointment(int patientId, int doctorId, PatientDoctor patientDoctor) throws AppointmentUnavailableException {
		PatientOPD patientOPD =  patientOPDRepository.findById(patientId).get(); 
		Doctor doctor =  doctorRepository.findById(doctorId).get(); 
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatientOpd(patientOPD);
		
		//check if given date is available 
		List<DoctorSchedule> doctorSchedule =  doctorRepository.getScheduleByDoctorId(doctorId);
		
		//reduce the list to only date 
		List<LocalDate> listDates =  doctorSchedule.stream().map(e->e.getDate()).toList();
		
		if(! (listDates.contains(patientDoctor.getDateOfAppointment()))) {
			throw new AppointmentUnavailableException("Appointment not available at this date"); 
		}
		
		List<String> listSlots = new ArrayList<>();
		for( DoctorSchedule ds :doctorSchedule ) {
			String fromTime = ds.getFromTime().toString().split(":00.000000")[0];
			String toTime = ds.getToTime().toString().split(":00.000000")[0];
			String slot = fromTime + "-" + toTime;
			listSlots.add(slot);
		}
		System.out.println("----list of all slots------");
		listSlots.stream().forEach(System.out :: println);
		
		System.out.println("Given slot: " + patientDoctor.getSlot());
		
		//check if given slot is in the list of  available slots 
		if( !(listSlots.contains(patientDoctor.getSlot()))) {
			throw new AppointmentUnavailableException("Appointment not available at this slot"); 
		}
		
		//check number of appointment availability 
		LocalTime fromTime = LocalTime.parse(patientDoctor.getSlot().split("-")[0]);    //10.00-12.00
		LocalTime toTime = LocalTime.parse(patientDoctor.getSlot().split("-")[1]);
		
		DoctorSchedule ds = doctorScheduleRepository.getNumberOfAppointmentCount(patientDoctor.getDateOfAppointment(), fromTime, toTime);
		if(ds.getNumberOfAppt() < 1) {
			throw new AppointmentUnavailableException("Appointments are full at this slot"); 
		}
		
		//book an appointment 
		patientDoctor.setDateOfBooking(LocalDate.now());
		ds.setNumberOfAppt(ds.getNumberOfAppt() - 1);; 
		doctorScheduleRepository.save(ds);
		return patientDoctorRepository.save(patientDoctor);
	}
	
	public List<PatientDoctor> findAppointmentsByPatient(int patientId) throws InvalidIdException {
        List<PatientDoctor> appointments = patientDoctorRepository.findByPatientOpdId(patientId);
        if (appointments.isEmpty()) {
            throw new InvalidIdException("No appointments found for Patient ID: " + patientId);
        }
        return appointments;
        
        
}
	
	public List<PatientDoctor> findAppointmentsByDoctor(int doctorId) throws InvalidIdException {
        List<PatientDoctor> appointments = patientDoctorRepository.findByDoctorId(doctorId);
        if (appointments.isEmpty()) {
            throw new InvalidIdException("No appointments found for Doctor ID: " + doctorId);
        }
        return appointments;
    }
}