package com.mamouros.backend.questions.Answer;

public class AnswerDto {
    private String text;
    private Boolean fromApp;
    private String time;

    private String author;

    public AnswerDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFromApp() {
        return fromApp;
    }

    public void setFromApp(Boolean fromApp) {
        this.fromApp = fromApp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
