/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int testId;
//    private int active;
    private String testName;
    private String description;
    private int testTime;
    private int questionNumber;
    private String password;

    @ManyToOne
    @JoinColumn(name = "testTypeId")
    private TestType testType;

    @ManyToMany
    @JoinTable(name = "test_question",
            joinColumns = @JoinColumn(name = "testId"),
            inverseJoinColumns = @JoinColumn(name = "questionId"))
    List<Question> questions = new ArrayList<>();

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getTestId() {
        return testId;
    }

    public String getTestName() {
        return testName;
    }

    public String getDescription() {
        return description;
    }

    public int getTestTime() {
        return testTime;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getPassword() {
        return password;
    }

    public TestType getTestType() {
        return testType;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Test() {
    }

}
