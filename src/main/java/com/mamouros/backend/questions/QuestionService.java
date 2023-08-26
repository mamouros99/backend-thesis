package com.mamouros.backend.questions;


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

    public void addNewQuestion(Question question) {


        questionRepository.save(question);
    }

    public Iterable<Question> getAllQuestions() {

        return questionRepository.findAll();
    }

    public void updateQuestion(Question question) {

        questionRepository.findById(question.getId()).orElseThrow(() -> new QuestionNotFoundException(question.getId()));
        questionRepository.save(question);
    }
}
