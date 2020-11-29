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
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 *
 * @author ducvuong25
 */
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    private String content;
    @Transient
    private boolean selected;
    private String ans1;
    private String ans2;
    private String ans3;
    private String crtAns;

    @ManyToMany(mappedBy = "questions", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Test> tests = new ArrayList<>();

    public Question() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getCrtAns() {
        return crtAns;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public void setCrtAns(String crtAns) {
        this.crtAns = crtAns;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

//    public void removeTest(Test test) {
//        test.getQuestions().remove(this);
//    }
    public void removeTest(Test test) {
        tests.remove(test);
        test.getQuestions().remove(this);
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", content=" + content + ", ans1=" + ans1 + ", ans2=" + ans2 + ", ans3=" + ans3 + ", crtAns=" + crtAns + ", tests=" + tests + '}';
    }
}
