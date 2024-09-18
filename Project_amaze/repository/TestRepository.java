package com.Project_amaze.repository;

import com.Project_amaze.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    @Query("SELECT t FROM Test t WHERE t.testStatus = 'PENDING'")
    List<Test> findPendingTests();

    @Query("SELECT t FROM Test t WHERE t.diagnosis.id = ?1 OR t.pathologist.id = ?2")
    List<Test> searchTestsByDiagnosisOrPathologist(int diagnosisId, int pathologistId);
}
