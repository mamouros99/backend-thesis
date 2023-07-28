package com.mamouros.backend.questions;


import com.mamouros.backend.exceptions.IslandNotFoundException;
import com.mamouros.backend.exceptions.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question getQuestionById(Long id){

        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public void addNewQuestion() {


        questionRepository.save(new Question());
    }
}
