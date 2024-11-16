package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongQuestion extends Question {
    private String answer;

    public LongQuestion(String question) {
        super(question);
    }

    @Override
    public void setAnswer(Object answer) {
        this.answer = (String) answer;
    }

    public LongQuestion(String question, String answer) {
        super(question);
        this.answer = answer;
    }
}
