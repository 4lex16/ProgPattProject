package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Grade {
    private Exam exam;
    @Setter
    private double value;
    private Student student;
    private int timesTaken;

    public Grade(Exam exam, double value, Student student, int timesTaken) {
        this.exam = exam;
        this.value = value;
        this.student = student;
        this.timesTaken = timesTaken;
    }

    public boolean isTaken() {
        return timesTaken > 0;
    }

    public void taken() {
        timesTaken++;
    }
}
