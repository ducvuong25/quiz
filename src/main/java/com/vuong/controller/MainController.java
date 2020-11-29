/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.controller;

import com.vuong.entity.Question;
import com.vuong.entity.Test;
import com.vuong.entity.TestType;
import com.vuong.service.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private WebService webService;

    // khi khoi dong project==> hien thi trang chu
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        webService.initData();
        return "index";
    }

    // khi click vao text link Question List
    @RequestMapping(path = "/listQuestion", method = RequestMethod.GET)
    public String toListQuestion(Model model) {
        model.addAttribute("questions", webService.getAllQuestion());
        return "list-question";
    }

    // khi click vao nut save trong form question (add or update)
    @RequestMapping(path = "/listQuestion", method = RequestMethod.POST)
    public String saveQuestion(@ModelAttribute Question question, Model model) {
        webService.addQuestion(question);
        return "redirect:/listQuestion";
    }

    // khi click vao button create question or link create question
    @RequestMapping(path = "/createQuestion", method = RequestMethod.GET)
    public String toCreateQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "create-question";
    }

    // khi click vao link  Test List
    @RequestMapping(path = "/listTest", method = RequestMethod.GET)
    public String listTest(Model model) {
        model.addAttribute("tests", webService.getAllTest());
        return "list-test";
    }

    //khi click vao button create Test hoac link create Test
    @RequestMapping(path = "/createTest", method = RequestMethod.GET)
    public String toCreateTest(Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("testTypes", getTestTypeMap());
        return "create-test";
    }

    // khi click vao nut save trom question form (add or update)
    @RequestMapping(path = "/createTest", method = RequestMethod.POST)
    public String saveTest(@ModelAttribute Test test) {
        webService.addTest(test);
        return "redirect:/listTest";
    }

    @RequestMapping(path = "/delete/{questionId}", method = RequestMethod.GET)
    public String deleteQuestion(@PathVariable int questionId) {
        webService.removeQuestion(questionId);
        return "redirect:/listQuestion";
    }

    @RequestMapping(path = "/edit/{questionId}", method = RequestMethod.GET)
    public String editQuestion(@PathVariable int questionId, Model model) {
        model.addAttribute("question", webService.getQuestionById(questionId));
        model.addAttribute("type", "update");
        return "create-question";
    }

    @RequestMapping(path = "/delete/test/{testId}", method = RequestMethod.GET)
    public String deleteTest(@PathVariable int testId, Model model) {
        webService.removeTest(testId);
        return "redirect:/listTest";
    }

    @RequestMapping(path = "/edit/test/{testId}", method = RequestMethod.GET)
    public String editTest(@PathVariable int testId, Model model) {
        model.addAttribute("test", webService.getTestById(testId));
        model.addAttribute("testTypes", getTestTypeMap());
        model.addAttribute("type", "update");
        return "create-test";
    }

    // khi click vao button create question (dua question vao test)
    @RequestMapping(path = "/inputQuestion/{testId}", method = RequestMethod.GET)
    public String inputQuestion(@PathVariable int testId, Model model) {

        List<Question> questions = webService.getAllQuestion();
        List<Question> CheckQuestion = webService.getTestById(testId).getQuestions();

        for (Question q : questions) {
            for (Question checkedq : CheckQuestion) {
                if (q.getQuestionId() == checkedq.getQuestionId()) {
                    q.setSelected(true);
                    break;
                }
            }
        }
        model.addAttribute("testId", testId);
        model.addAttribute("questions", questions);
        return "input-question";
    }

    // khi click vao button save  trong form input question
    @RequestMapping(path = "/addQuestionToTest", method = RequestMethod.POST)
    public String addQuestionToTest(@RequestParam(required = false, defaultValue = "") int[] questionId, @RequestParam int testId) {
        webService.addquestionToTest(testId, questionId);
        return "redirect:/listTest";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("questions", webService.searchQuestion(keyword));
        return "list-question";
    }

    // method nay dung de tao map bookType
    public Map<String, String> getTestTypeMap() {
        Map<String, String> testTypes = new HashMap<>();
        List<TestType> testTypeList = webService.getAllTestType();
        for (TestType testType : testTypeList) {
            testTypes.put(testType.getTestTypeID(), testType.getTesttypeName());
        }
        return testTypes;
    }

}
