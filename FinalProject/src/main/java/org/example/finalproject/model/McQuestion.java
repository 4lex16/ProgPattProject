package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class McQuestion extends Question {
    private char answer;
    private String[] options;


    public McQuestion(String question) {
        super(question);
    }

    public McQuestion(String question, char answer, String... options) {
        super(question);
        this.answer = answer;
        this.options = options;
    }
}
