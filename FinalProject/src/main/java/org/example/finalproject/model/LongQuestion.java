package org.example.finalproject.model;

public class LongQuestion extends Question {
    private String answer;

    public LongQuestion(String question) {
        super(question);
    }

    public LongQuestion(String question, String answer) {
        super(question);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
