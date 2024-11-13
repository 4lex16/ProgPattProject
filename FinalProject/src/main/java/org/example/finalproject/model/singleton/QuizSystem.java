package org.example.finalproject.model.singleton;


import lombok.Getter;
import lombok.Setter;
import org.example.finalproject.model.Student;
import org.example.finalproject.model.Teacher;
import org.example.finalproject.model.Topic;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuizSystem {
    private static QuizSystem instance;

    @Setter
    public List<Teacher> teachers;
    @Setter
    public List<Student> students;
    @Setter
    public List<Topic> topics;


    private QuizSystem() {
        // TODO: Get the information from the database
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        topics = new ArrayList<>();
    }

    public static QuizSystem getInstance() {
        if (instance == null) {
            synchronized (QuizSystem.class) {
                if (instance == null) {
                    instance = new QuizSystem();
                }
            }
        }
        return instance;
    }
}
