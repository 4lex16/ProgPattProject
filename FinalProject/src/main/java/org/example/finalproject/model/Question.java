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

    /**
     * Creates a LongQuestion if 2 Strings are inputted
     * @param question String input
     * @param answer String input
     * @return LongQuestion that was created
     */
    public static Question createQuestion(String question, String answer) {
        return new LongQuestion(question, answer);
    }

    /**
     * Creates a McQuestion if a String and a char are inputted
     * @param question String input
     * @param answer char input
     * @return McQuestion that was created
     */
    public static Question createQuestion(String question, char answer) {
        return new McQuestion(question, answer);
    }

    /**
     * Creates a TfQuestion if a String and a boolean are inputted
     * @param question String input
     * @param answer boolean input
     * @return TfQuestion that was created
     */
    public static Question createQuestion(String question, boolean answer) {
        return new TfQuestion(question, answer);
    }
}
