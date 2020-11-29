/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ducvuong25
 */
@Entity
public class TestType {

    @Id
    private String testTypeID;
    private String testtypeName;

    @OneToMany(mappedBy = "testType")
    List<Test> tests;

    


    public TestType() {
    }

    public String getTestTypeID() {
        return testTypeID;
    }

    public String getTesttypeName() {
        return testtypeName;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTestTypeID(String testTypeID) {
        this.testTypeID = testTypeID;
    }

    public void setTesttypeName(String testtypeName) {
        this.testtypeName = testtypeName;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

}
