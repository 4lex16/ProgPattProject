package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TfQuestion extends Question {
    private boolean answer;

    public TfQuestion(String question) {
        super(question);
    }

    @Override
    public void setAnswer(Object answer) {
        this.answer = (boolean) answer;
    }

    public TfQuestion(String question, boolean answer) {
        super(question);
        this.answer = answer;
    }
}
