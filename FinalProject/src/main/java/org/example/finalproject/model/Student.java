package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Student extends User {
    List<Test> tests;
    List<Quiz> quizzes;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, String type, List<Test> tests, List<Quiz> quizzes) {
        super(firstName, lastName, email, password, type);
        this.tests = tests;
        this.quizzes = quizzes;
    }
}
