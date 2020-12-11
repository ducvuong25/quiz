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
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    private WebService webService;

    // khi click vao menu List Test
    @RequestMapping()
    public String listTest(Model model) {
        model.addAttribute("tests", webService.getAllTest());
        return "list-test";
    }

    //khi click vao button create Test hoac link create Test
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String toCreateTest(Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("testTypes", getTestTypeMap());
        return "create-test";
    }

    // khi click vao button edit
    @RequestMapping(path = "/edit/{testId}", method = RequestMethod.GET)
    public String editTest(@PathVariable int testId, Model model) {
        model.addAttribute("test", webService.getTestById(testId));
        model.addAttribute("testTypes", getTestTypeMap());
        model.addAttribute("type", "update");
        return "create-test";
    }

    // khi click vao nut save trom question form (add or edit)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String saveTest(@ModelAttribute Test test) {
        webService.addTest(test);
        return "redirect:/test";
    }

    // khi click vao button delete
    @RequestMapping(path = "/delete/{testId}", method = RequestMethod.GET)
    public String deleteTest(@PathVariable int testId, Model model) {
        webService.removeTest(testId);
        return "redirect:/test";
    }

    // khi click vao button create question (dua question vao test)
    @RequestMapping(path = "/addQuestion/{testId}", method = RequestMethod.GET)
    public String inputQuestion(@PathVariable int testId, Model model) {
        // list toan bo cau hoi
        List<Question> questions = webService.getAllQuestion();

        Test test = webService.getTestById(testId);
        //list cac cau hoi da chon truoc do
        List<Question> CheckQuestion = test.getQuestions();

        for (Question q : questions) {
            for (Question checkedq : CheckQuestion) {
                if (q.getQuestionId() == checkedq.getQuestionId()) {
                    // thiet lap flag selected la true de dung lam dieu kien trong jsp
                    q.setSelected(true);
                    break;
                }
            }
        }
        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        return "input-question";
    }

    // khi click vao button save  trong form input question
    @RequestMapping(path = "/addQuestion", method = RequestMethod.POST)
    public String addQuestionToTest(@RequestParam(required = false, defaultValue = "") int[] questionId,
            @RequestParam int testId) {
        webService.addquestionToTest(testId, questionId);
        return "redirect:/test";
    }

    // method nay dung de tao map bookType hien thi tren  drop down list
    public Map<String, String> getTestTypeMap() {
        Map<String, String> testTypes = new HashMap<>();
        List<TestType> testTypeList = webService.getAllTestType();
        for (TestType testType : testTypeList) {
            testTypes.put(testType.getTestTypeID(), testType.getTesttypeName());
        }
        return testTypes;
    }

}
