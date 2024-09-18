package com.Project_amaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project_amaze.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Integer>{

}