package com.Project_amaze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project_amaze.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer>{

	List<PatientDoctor> findByPatientOpdId(int patientId);
	List<PatientDoctor> findByDoctorId(int doctorId);

}
