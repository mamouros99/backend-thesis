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
    public @ResponseBody void addNewQuestion(@RequestBody QuestionDto questionDto){
        System.out.println(questionDto);
        Question question = new Question();
        question.setQuestion(questionDto.getQuestion());
        question.setTime(questionDto.getTime());
        question.setEmail(questionDto.getEmail());
        questionService.addNewQuestion(question);
    }

    @DeleteMapping( "/delete/{id}")
    public @ResponseBody void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    @GetMapping("/get/{id}")
    public @ResponseBody Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/get/all")
    public @ResponseBody Iterable<Question> getAllQuestions(){

        return questionService.getAllQuestions();
    }

    @PutMapping("/put")
    public @ResponseBody void addAnswerToQuestion(@RequestBody Question question ){

        question.setChecked(true);
        questionService.updateQuestion(question);
    }

}
