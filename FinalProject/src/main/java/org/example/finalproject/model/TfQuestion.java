package org.example.finalproject.model;

public class TfQuestion extends Question {
    private boolean answer;

    public TfQuestion(String question) {
        super(question);
    }

    public TfQuestion(String question, boolean answer) {
        super(question);
        this.answer = answer;
    }
}
