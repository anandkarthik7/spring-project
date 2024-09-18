package com.Project_amaze.controller;

import com.Project_amaze.dto.TestDto;
import com.Project_amaze.model.*;
import com.Project_amaze.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pathologist")
public class PathologistController {

    @Autowired
    private PathologistService pathologistService;
    
    
    @PostMapping("/tests/addTest")
    public ResponseEntity<Test> addTest(@RequestBody Test newTest) {
        try {
            Test createdTest = pathologistService.addTest(newTest);
            return ResponseEntity.ok(createdTest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/tests/pending")
    public ResponseEntity<List<Test>> getPendingTests() {
        List<Test> tests = pathologistService.getPendingTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/tests/search")
    public ResponseEntity<List<Test>> searchTests(
            @RequestParam int diagnosisId,
            @RequestParam int pathologistId) {
        List<Test> tests = pathologistService.searchTests(diagnosisId, pathologistId);
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/tests/{testId}/result")
    public ResponseEntity<?> addTestResult(@PathVariable int testId, @RequestBody TestDto testResult) {
        try {
            Test updatedTest = pathologistService.addTestResult(testId, testResult);
            return ResponseEntity.ok(updatedTest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
