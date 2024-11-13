package org.example.finalproject.controller;

import org.example.finalproject.model.*;

import java.util.List;

public class TeacherController {
    private Teacher teacher;

    TeacherController(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean addStudent(Student student, Group group) {
        // TODO Create addStudent() method
        return false;
    }

    public boolean createGroup(Topic topic) {
        // TODO Create addGroup() method
        return false;
    }

    public boolean createGroup(Topic topic, List<Student> students) {
        // TODO Create createGroup() method
        return false;
    }

    public boolean createQuiz(Question... questions) {
        // TODO Create createQuiz() method
        return false;
    }

    public boolean removeQuiz(Quiz quiz) {
        // TODO Create removeQuiz() method
        return false;
    }

    public boolean createTest(Question... questions) {
        // TODO Create createTest() method
        return false;
    }

    public boolean removeTest(Test test) {
        // TODO Create removeTest() Method
        return false;
    }

    public boolean addQuestion(Exam exam, Question... question) {
        // TODO Create addQuestion() method
        return false;
    }

    public boolean removeQuestion(Exam exam, Question question) {
        // TODO Create removeQuestion() method
        return false;
    }

    public boolean createQuestion(String type, String question, Object answer) {
        // TODO Create createQuestion() method
        return false;
    }

    public boolean removeQuestion(Question question) {
        // TODO Create removeQuestion() method
        return false;
    }

    public boolean editQuestion(Question question, String questionText, Object answer) {
        // TODO Create editQuestion() method
        return false;
    }

    public boolean gradeQuiz(Student student, Quiz quiz, double grade) {
        // TODO Create gradeQuiz() method
        return false;
    }

    public boolean gradeTest(Student student, Test test, double grade) {
        // TODO Create gradeTest() method
        return false;
    }
}
