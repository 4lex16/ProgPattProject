package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Grade {
    private Exam exam;
    @Setter
    private double value;
    private Student student;

    public Grade(Exam exam, double value, Student student) {
        this.exam = exam;
        this.value = value;
        this.student = student;
    }


}
