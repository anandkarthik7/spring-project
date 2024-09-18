package com.Project_amaze.repository;

import com.Project_amaze.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PathologistRepository extends JpaRepository<Pathologist, Integer> {

}
