package org.example.finalproject.model.factory;

import org.example.finalproject.model.LongQuestion;
import org.example.finalproject.model.McQuestion;
import org.example.finalproject.model.Question;
import org.example.finalproject.model.TfQuestion;

import java.security.InvalidParameterException;

public class QuizFactory {

    public static Question createQuestion(String type, String question, Object answer) {
        switch (type) {
            case "mc" -> {
                return new McQuestion(question, (char) answer);
            }
            case "tf" -> {
                return new TfQuestion(question, (boolean) answer);
            }
            case "long" -> {
                return new LongQuestion(question, (String) answer);
            }
            default -> throw new InvalidParameterException(type);
        }
    }
}
