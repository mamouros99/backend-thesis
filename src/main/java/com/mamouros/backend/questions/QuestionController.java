package com.mamouros.backend.questions;

import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.auth.User.UserService;
import com.mamouros.backend.questions.Answer.AnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping( "/add")
    public @ResponseBody void addNewQuestion(@RequestBody QuestionDto questionDto){

        Question question = new Question();
            question.setQuestion(questionDto.getQuestion());
            question.setTime(questionDto.getTime());
            question.setShowQuestion(true);

        if(questionDto.getUsername() != null) {
            User user = userService.findByUsername(questionDto.getUsername());
            question.setUser(user);
        }

        questionService.addNewQuestion(question);
    }



    @DeleteMapping( "/delete/{id}")
    public @ResponseBody void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    @PostMapping("/answer/{question_id}")
    public @ResponseBody void AddNewAnswer(@PathVariable Long question_id, @RequestBody AnswerDto answer){
        questionService.addNewAnswer(question_id, answer);
    }

    @GetMapping("/get/{id}")
    public @ResponseBody Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }


    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PutMapping("/archive/{id}")
    public @ResponseBody void archiveQuestion(@PathVariable Long id){
         questionService.archiveQuestion(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PutMapping("/unarchive/{id}")
    public @ResponseBody void unarchiveQuestion(@PathVariable Long id){
        questionService.unarchiveQuestion(id);
    }

    //Stop showing question in mobile
    @PutMapping("/hide/{id}")
    public @ResponseBody void hideQuestion(@PathVariable Long id){
        questionService.hideQuestion(id);
    }

    @GetMapping("/get/all/{username}")
    public @ResponseBody Iterable<Question> getAllQuestionsByUsername(@PathVariable String username){
        return questionService.getAllQuestionsByUsername(username);
    }

    @GetMapping("/get/all")
    public @ResponseBody Iterable<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/get/count")
    public @ResponseBody long countAllQuestions(){
        return questionService.countAllQuestions();
    }



}
