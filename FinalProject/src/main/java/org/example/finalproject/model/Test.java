package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Test extends Exam {
    private List<String> answer;

    public Test() {
        answer = new ArrayList<>();
    }

    public Test(int id, List<Question> questions, List<Grade> grades) {
        this.id = id;
        this.questions = questions;
        this.grades = grades;
        this.answer = new ArrayList<>();
    }
}
