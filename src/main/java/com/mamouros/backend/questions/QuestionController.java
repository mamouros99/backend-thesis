package com.mamouros.backend.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping( "/add")
    public @ResponseBody void addNewQuestion(@RequestBody String email){
        questionService.addNewQuestion();
    }

    @DeleteMapping( "/delete/{id}")
    public @ResponseBody void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    @GetMapping("/get/{id}")
    public @ResponseBody Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }
}
