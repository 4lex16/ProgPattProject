package org.example.finalproject.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.finalproject.model.Quiz;
import org.example.finalproject.model.Student;

@Getter
@Setter
public class StudentController {
    private Student student;

    public StudentController(Student student) {
        this.student = student;
    }

    public void doQuiz(Quiz quiz) {
        quiz.taken();
    }

    public void doTest(Quiz test) {
        test.taken();
    }
}
