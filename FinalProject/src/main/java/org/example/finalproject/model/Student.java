package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Student extends User {
    List<Test> tests;
    List<Quiz> quizzes;
    List<Group> groups;

    public Student() {
        tests = new ArrayList<>();
        quizzes = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public Student(String firstName, String lastName, String email, String password, String type) {
        super(firstName, lastName, email, password, type);
        groups = new ArrayList<>();
        tests = new ArrayList<>();
        quizzes = new ArrayList<>();
    }
}
