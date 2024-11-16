package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

public class Question {
    private static int counter = 0;
    @Getter
    @Setter
    protected int id;
    @Getter
    @Setter
    protected String question;

    public Question(String question) {
        id = ++counter;
        this.question = question;
    }

}
