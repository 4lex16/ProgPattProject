package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class McQuestion extends Question {
    public char answer;

    public McQuestion(String question) {
        super(question);
    }

    public McQuestion(String question, char answer) {
        super(question);
        this.answer = answer;
    }
}
