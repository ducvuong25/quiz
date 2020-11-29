/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.service;

import com.vuong.entity.Question;
import com.vuong.entity.Test;
import com.vuong.entity.TestType;
import java.util.List;

/**
 *
 * @author ducvuong25
 */
public interface WebService {

    void initData();

    void addQuestion(Question question);

    Question getQuestionById(int questionId);

    List<Question> getAllQuestion();

    List<Question> GetQuestionsById(int[] questions);

    void removeQuestion(int questionId);

    // lam viec voi test
    Test getTestById(int testId);

    void addTest(Test test);

    List<Test> getAllTest();

    void removeTest(int testId);

    void addquestionToTest(int TestID, int[] questionId);

    // lam viec voi testtype
    List<TestType> getAllTestType();

    List<Question> searchQuestion(String keyword);
    

}
