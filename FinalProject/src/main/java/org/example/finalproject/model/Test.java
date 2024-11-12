package org.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Test extends Exam {
    @Setter
    private boolean isTaken;
    private List<String> answer;

    public Test() {
        isTaken = false;
        answer = new ArrayList<>();
    }
}
