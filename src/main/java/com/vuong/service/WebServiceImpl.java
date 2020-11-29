/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.service;

import com.vuong.entity.Question;
import com.vuong.entity.Test;
import com.vuong.entity.TestType;
import com.vuong.repository.QuestionRepository;
import com.vuong.repository.TestRepository;
import com.vuong.repository.TestTypeRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestTypeRepository testTypeRepository;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Override
    public void initData() {
        // tao 2 category

        TestType mathTest = new TestType();
        mathTest.setTestTypeID("Math");
        mathTest.setTesttypeName("Test For Math");

        TestType iTTest = new TestType();
        iTTest.setTestTypeID("IT");
        iTTest.setTesttypeName("Test For IT");

        testTypeRepository.save(mathTest);
        testTypeRepository.save(iTTest);

    }

    @Override
    public void addQuestion(Question question) {
        questionRepository.save(question);

    }

    @Override
    public List<Question> getAllQuestion() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public void removeQuestion(int questionId) {
        // da thu nhieu cach, cuoi cung chi lam duoc cach nay
        EntityManagerFactory emf = entityManagerFactory.getObject();
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Question question = entityManager.find(Question.class, questionId);
        for (Test test : new ArrayList<>(question.getTests())) {
            question.removeTest(test);
        }
        entityManager.remove(question);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Question getQuestionById(int questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Test getTestById(int testId) {
        return testRepository.findById(testId).get();
    }

    @Override
    public List<Test> getAllTest() {
        return (List<Test>) testRepository.findAll();

    }

    @Override
    public void removeTest(int testId) {
        testRepository.deleteById(testId);
    }

    @Override
    public List<TestType> getAllTestType() {
        return (List<TestType>) testTypeRepository.findAll();
    }

    @Override
    public void addTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void addquestionToTest(int TestID, int[] questionId) {

        Test test = getTestById(TestID);
        test.getQuestions().clear();

        if (questionId.length != 0) {
            List<Question> questions = GetQuestionsById(questionId);
            test.setQuestions(questions);
        }
        testRepository.save(test);
    }

    @Override
    public List<Question> GetQuestionsById(int[] list) {
        List<Question> questions = new ArrayList<>();

        for (Integer questionId : list) {
            questions.add(getQuestionById(questionId));
        }
        return questions;
    }

    @Override
    public List<Question> searchQuestion(String keyword) {
        return questionRepository.findByContentContaining(keyword);
    }

}
