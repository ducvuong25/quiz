package com.vuong.service;

import com.vuong.entity.Question;
import com.vuong.entity.Test;
import com.vuong.entity.TestType;
import com.vuong.repository.QuestionRepository;
import com.vuong.repository.TestRepository;
import com.vuong.repository.TestTypeRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestTypeRepository testTypeRepository;

    @Override
    @PostConstruct
    public void initData() {
        // tao 2 TestType đẻ vào database để hiển thị lên dropdown JSP

        TestType mathTest = new TestType();
        mathTest.setTestTypeID("Math");
        mathTest.setTesttypeName("Test For Math");

        TestType iTTest = new TestType();
        iTTest.setTestTypeID("IT");
        iTTest.setTesttypeName("Test For IT");

        TestType engTest = new TestType();
        engTest.setTestTypeID("English");
        engTest.setTesttypeName("Test For English");

        testTypeRepository.save(mathTest);
        testTypeRepository.save(iTTest);
        testTypeRepository.save(engTest);

        
          // tao 4 question
        Question q1 = new Question();
        q1.setContent("The first letter of the first word in a sentence should be");
        q1.setAns1("a large letter");
        q1.setAns2("a capital letter");
        q1.setAns3("a small letter");
        q1.setCrtAns("a large letter");

        Question q2 = new Question();
        q2.setContent("The order of a basic positive sentence is");
        q2.setAns1("Subject-Verb-Object ");
        q2.setAns2("Verb-Object-Subject");
        q2.setAns3("Adjective-Object-Subject");
        q2.setCrtAns("Verb-Object-Subject");

        Question q3 = new Question();
        q3.setContent("Verb-Object-Subject");
        q3.setAns1("a verb");
        q3.setAns2("an object");
        q3.setAns3("a noun");
        q3.setCrtAns("a noun");

        Question q4 = new Question();
        q4.setContent("A plural subject needs");
        q4.setAns1(" a singular verb");
        q4.setAns2(" a plural verb");
        q4.setAns3("a countable verb");
        q4.setCrtAns("a countable verb");
        
        //tao 1 test
        Test test = new Test();
        test.setTestName("English for kid");
        test.setDescription("This test for basic english");
        test.setQuestionNumber(4);
        test.setTestTime(30);
        test.setPassword("vuongcute");
        test.setTestType(engTest);
        test.getQuestions().add(q1);
        test.getQuestions().add(q2);
        test.getQuestions().add(q3);
        test.getQuestions().add(q4);
        
        // luu test do vao
        addTest(test);
        

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
    @Transactional
    public void removeQuestion(int questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        for (Test test : new ArrayList<>(question.getTests())) {
            question.removeTest(test);
        }
        questionRepository.delete(question);
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
        // lay ra bai test do
        Test test = getTestById(TestID);
        // lay toan bo question duoc check
        List<Question> questions = GetQuestionsById(questionId);
        // tu bo list quetion cu va them list question duoc chọn
        test.setQuestions(questions);

        // Luu vao database
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
