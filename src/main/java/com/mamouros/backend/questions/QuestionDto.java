package com.mamouros.backend.questions;

import java.io.Serializable;

public class QuestionDto implements Serializable {

    private String question;
    private String time;
    private String email;

    private String username;

    public QuestionDto(String question, String email, String time, String username) {
        this.question = question;
        this.email = email;
        this.time = time;
        this.username = username;
    }

    public QuestionDto() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "question='" + question + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '}';
    }
}
