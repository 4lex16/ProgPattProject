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
    protected List<Grade> grades;

    public Exam() {
        id = ++counter;
        questions = new ArrayList<>();
        grades = new ArrayList();
    }

    public Exam(int id, List<Question> questions, List<Grade> grades) {
        this.id = id;
        this.questions = questions;
        this.grades = grades;
    }
}
