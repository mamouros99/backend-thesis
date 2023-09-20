package com.mamouros.backend.questions;


import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.auth.User.UsersRepository;
import com.mamouros.backend.email.EmailService;
import com.mamouros.backend.exceptions.QuestionNotFoundException;
import com.mamouros.backend.helpers.GlobalHelper;
import com.mamouros.backend.questions.Answer.Answer;
import com.mamouros.backend.questions.Answer.AnswerDto;
import com.mamouros.backend.questions.Answer.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;

    @Value("${desktop.url}")
    private String myURL;

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

        List<User> users = (List<User>) usersRepository.findAllByReceiveQuestionsTrue();

        for (User user: users ) {
            emailService.sendEmail(user.getEmail(), "Nova pergunta TFaD", "Uma nova pergunta foi efetuada às " +
                Instant.ofEpochMilli(Long.parseLong(question.getTime())).atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss d-MM-yyyy"))
                + ". \n"
                + "Para aceder à pergunta siga o link: " + myURL + "/question/" + question.getId()
                + ". \n"
                + question.getUser().getName() + " enviou a seguinte mensagem: \n"
                + "\t" + question.getQuestion() + "\n"

            );
        }

    }

    public Iterable<Question> getAllQuestions() {

        return questionRepository.findAll();
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

        if(answer.isFromApp())
            return;

        List<User> users = (List<User>) usersRepository.findAllByReceiveQuestionsTrue();

        for (User user: users ) {
            emailService.sendEmail(user.getEmail(), "Nova resposta à pergunta TFaD", "Uma nova resposta foi efetuada às " +
                    Instant.ofEpochMilli(Long.parseLong(answer.getTime())).atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss d-MM-yyyy"))
                    + ". \n"
                    + "Para aceder à pergunta siga o link: " + myURL + "/question/" + question.getId()
                    + ". \n"
                    + question.getUser().getName() + " enviou a seguinte resposta: \n"
                    + "\t" + answer.getText() + "\n"

            );
        }

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


    public void hideQuestion(Long id) {
        User user = GlobalHelper.getUserFromSecurityContext();

        //check if user is same as the one in question
        Question question = getQuestionById(id);
        if (!Objects.equals(question.getUser().getUsername(), user.getUsername())){
            throw new RuntimeException("You dont have permission to change this question");
        }

        question.setShowQuestion(false);
        questionRepository.save(question);

    }

    public long countAllQuestions() {

        List<Question> questions = (List<Question>) getAllQuestions();
        return questions.stream().filter(question -> !question.getArchived()).count();
    }
}
