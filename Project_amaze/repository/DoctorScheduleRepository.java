package com.Project_amaze.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Project_amaze.model.DoctorSchedule;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Integer>{

	@Query("select ds from DoctorSchedule ds where ds.date=?1 AND ds.fromTime = ?2 AND ds.toTime=?3")
	DoctorSchedule getNumberOfAppointmentCount(LocalDate dateOfAppointment, LocalTime fromTime, LocalTime toTime);

}