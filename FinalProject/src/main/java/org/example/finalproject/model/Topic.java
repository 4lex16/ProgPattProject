package org.example.finalproject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Topic {
    private int topicId;
    private static int counter = 0;
    @Setter
    private String title;
    @Setter
    private List<Quiz> quizzes;
    @Setter
    private List<Test> tests;
    @Setter
    private List<Question> questions;

    public Topic() {
        topicId = counter++;
    }

    public Topic(String title, List<Quiz> quizzes, List<Test> tests, List<Question> questions) {
        this.title = title;
        this.quizzes = quizzes;
        this.tests = tests;
        this.questions = questions;
        topicId = ++counter;
    }
}
