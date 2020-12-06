    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.controller;

import com.vuong.entity.Question;
import com.vuong.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    private WebService webService;

    // hien thi List question
    @RequestMapping()
    public String toListQuestion(Model model) {
        model.addAttribute("questions", webService.getAllQuestion());
        return "list-question";
    }

    // khi click vao button create question or link create question
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String toCreateQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "create-question";
    }

    // khi click vao nut save trong form question (add)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String saveQuestion(@ModelAttribute Question question, Model model) {
        webService.addQuestion(question);
        return "redirect:/question";
    }
    
    // khi click vao nut save trong form (edit)
 @RequestMapping(path = "/edit/{questionId}", method = RequestMethod.GET)
    public String editQuestion(@PathVariable int questionId, Model model) {
        model.addAttribute("question", webService.getQuestionById(questionId));
        model.addAttribute("type", "update");
        return "create-question";
    }

   
    @RequestMapping(path = "/delete/{questionId}", method = RequestMethod.GET)
    public String deleteQuestion(@PathVariable int questionId) {
        webService.removeQuestion(questionId);
        return "redirect:/question";
    }

   
    // tim kiem theo ten question o moi trang man hinh
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("questions", webService.searchQuestion(keyword));
        return "list-question";
    }


}
