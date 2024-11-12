package org.example.finalproject.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Exam {
    private static int counter = 0;
    protected int id;
    @Setter
    protected List<Question> questions;
    @Setter
    protected Grade grade;

    public Exam() {
        id = ++counter;
        questions = new ArrayList<>();
        grade = null;
    }
}
