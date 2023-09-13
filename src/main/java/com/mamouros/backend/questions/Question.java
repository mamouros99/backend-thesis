package com.mamouros.backend.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.questions.Answer.Answer;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String question;

    @OneToMany( mappedBy = "question")
    private List<Answer> answers;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private Boolean archived;

    public Question() {
        this.archived = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer){

        this.answers.add(answer);

    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", user=" + user.getUsername() +
                ", time='" + time + '\'' +
                ", archived=" + archived +
                '}';
    }
}
