package com.Project_amaze.service;
import com.Project_amaze.dto.TestDto;
import com.Project_amaze.model.*;
import com.Project_amaze.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathologistService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getPendingTests() {
        return testRepository.findPendingTests();
    }

    public List<Test> searchTests(int diagnosisId, int pathologistId) {
        return testRepository.searchTestsByDiagnosisOrPathologist(diagnosisId, pathologistId);
    }

   public Test addTestResult(int testId, TestDto updatedTest) throws Exception {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new Exception("Test not found"));
TestDto testDto=new TestDto();   
test.setTestResult(testDto.getTestResult());
        return testRepository.save(test);
    }


	   public Test addTest(Test newTest) {
		        // Create a new Test entity from the DTO
		        Test newTest1 = new Test();
		        newTest1.setTestName(newTest1.getTestName());
		        newTest1.setDescription(newTest1.getDescription());
		        newTest1.setPrice(newTest1.getPrice());

		        // Save the new test to the database
		        return testRepository.save(newTest1);
		    }
	    };


