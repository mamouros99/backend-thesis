package com.mamouros.backend.questions;


import com.mamouros.backend.exceptions.QuestionNotFoundException;
import com.mamouros.backend.questions.Answer.Answer;
import com.mamouros.backend.questions.Answer.AnswerDto;
import com.mamouros.backend.questions.Answer.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Question getQuestionById(Long id){

        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    public void deleteQuestion(Long id) {

        Question question = getQuestionById(id);
        deleteQuestionAnswers(question);

        questionRepository.delete(question);
    }

    public void deleteQuestionAnswers(Question question){

        answerRepository.deleteAll(question.getAnswers());

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

    public void addNewAnswer(Long questionId, AnswerDto answerDto) {

        Question question = getQuestionById(questionId);

        if(question.getArchived())
            return;

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setFromApp(answerDto.getFromApp());
        answer.setText(answerDto.getText());
        answer.setTime(answerDto.getTime());
        answer.setViewed(false);


        question.addAnswer(answer);

        answerRepository.save(answer);
        questionRepository.save(question);

    }

    public Iterable<Question> getAllQuestionsByUsername(String username) {

        return questionRepository.findAllByUsername(username);
    }

    public void archiveQuestion(Long id) {

        Question question = getQuestionById(id);
        question.setArchived(true);
        questionRepository.save(question);

    }

    public void unarchiveQuestion(Long id) {
        Question question = getQuestionById(id);
        question.setArchived(false);
        questionRepository.save(question);
    }


}
