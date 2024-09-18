package com.Project_amaze.model;

import com.Project_amaze.enums.TestName;
import com.Project_amaze.enums.TestResult;
import com.Project_amaze.enums.TestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Test {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Enumerated(EnumType.STRING)
    private TestName testName;
    
	@Column
    private String description;
	@Column
    private int price;
	@Enumerated(EnumType.STRING)
	private TestResult testResult;
	@Enumerated(EnumType.STRING)
	private TestStatus testStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TestName getTestName() {
		return testName;
	}
	public void setTestName(TestName testName) {
		this.testName = testName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public TestResult getTestResult() {
		return testResult;
	}
	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
	public TestStatus getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(TestStatus testStatus) {
		this.testStatus = testStatus;
	}
	public Test(int id, TestName testName, String description, int price, TestResult testResult,
			TestStatus testStatus) {
		super();
		this.id = id;
		this.testName = testName;
		this.description = description;
		this.price = price;
		this.testResult = testResult;
		this.testStatus = testStatus;
	}
	public Test() {
		// TODO Auto-generated constructor stub
	}

	
}