package com.mamouros.backend.questions;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mamouros.backend.auth.User.User;
import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String question;

    private String answer;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String time;

    private Boolean checked;

    public Question() {
        this.checked = false;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", email='" + email + '\'' +
                ", time='" + time + '\'' +
                ", checked=" + checked +
                '}';
    }
}
