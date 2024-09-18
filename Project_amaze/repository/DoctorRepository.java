package com.Project_amaze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Project_amaze.model.Doctor;
import com.Project_amaze.model.DoctorSchedule;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	@Query("select ds from DoctorSchedule ds where ds.doctor.id =?1")
	List<DoctorSchedule> getScheduleByDoctorId(int doctorId);

}