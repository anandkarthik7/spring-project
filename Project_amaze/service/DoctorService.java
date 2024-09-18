package com.Project_amaze.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project_amaze.exception.InvalidIdException;
import com.Project_amaze.model.Doctor;
import com.Project_amaze.model.DoctorSchedule;
import com.Project_amaze.model.User;
import com.Project_amaze.repository.DoctorRepository;
import com.Project_amaze.repository.DoctorScheduleRepository;
import com.Project_amaze.repository.UserRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoctorScheduleRepository doctorScheduleRepository;
	
	public Doctor addDcotor(Doctor doctor) {
		 /* extract UserInfo from doctor  */
		User info = doctor.getUser();
		
		/*Encrypt password and assign Role to doctor via UserInfo*/
		info.setPassword(passwordEncoder.encode(info.getPassword()));
		info.setRole("ROLE_DOCTOR");
		
		/* save UserInfo */
		info = userRepository.save(info); //fully prepared obj  with id, role and pass-encoded
		
		/* Attach Attach fully prepared UserInfo obj to Doctor*/
		doctor.setUser(info);
		
		/*Save Doctor */
		return doctorRepository.save(doctor);
	}

	public DoctorSchedule addDoctorSchedule(int doctorId, DoctorSchedule doctorSchedule) throws InvalidIdException {
		/* fetch doctor basis doctorId  */
		Optional<Doctor> optional =  doctorRepository.findById(doctorId); 
		if(optional.isEmpty())
			throw new InvalidIdException("Doctor ID Invalid");
		
		Doctor doctor = optional.get();
		/* attach doctor to doctorSchedule */
		doctorSchedule.setDoctor(doctor);
		
		/* save doctorSchedule */
		return doctorScheduleRepository.save(doctorSchedule);
	}
}
