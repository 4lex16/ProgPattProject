package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Grade {
    private Exam exam;
    @Setter
    private double value;

    public Grade(Exam exam, double value) {
        this.exam = exam;
        this.value = value;
    }


}
