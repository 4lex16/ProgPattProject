package org.example.finalproject.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Quiz extends Exam {

    public Quiz() {

    }

    public Quiz(int id, List<Question> questions, List<Grade> grades) {
        this.id = id;
        this.questions = questions;
        this.grades = grades;
    }
}
