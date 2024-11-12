package org.example.finalproject.model;

import lombok.Getter;

@Getter
public class Quiz extends Exam {
    private int timesTaken;

    public Quiz() {
        timesTaken = 0;
    }

    public void taken() {
        // Maybe lock it?
        timesTaken++;
    }
}
