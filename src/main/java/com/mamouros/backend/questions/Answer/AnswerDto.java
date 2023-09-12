package com.mamouros.backend.questions.Answer;

public class AnswerDto {
    private String text;
    private Boolean fromApp;
    private String time;

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
}
