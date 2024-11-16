package org.example.finalproject.controller;

import org.example.finalproject.model.*;
import org.example.finalproject.model.factory.QuestionFactory;

import java.util.List;

public class TeacherController {
    private Teacher teacher;

    TeacherController(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Adds a student to a group and adds the group to the group list for that student
     *
     * @param student student to be added
     * @param group   group to add the student
     */
    private void addStudent(Student student, Group group) {
        group.getStudents().add(student);
        student.getGroups().add(group);
    }

    /**
     * Creates a new group and adds it to the list of groups in Teacher class
     *
     * @param topic the topic of the new group
     */
    private void createGroup(Topic topic) {
        Group group = new Group(topic);
        teacher.getGroups().add(group);
    }

    /**
     * Creates a new quiz and adds it to the topic's list of quizzes
     *
     * @param topic The topic of the quiz
     */
    private void createQuiz(Topic topic) {
        Quiz quiz = new Quiz();
        topic.getQuizzes().add(quiz);
    }

    /**
     * Creates a new test and adds it to the topic's list of tests
     *
     * @param topic The topic of the test
     */
    private void createTest(Topic topic) {
        Test test = new Test();
        topic.getTests().add(test);
    }

    /**
     * adds a question to a quiz/test
     *
     * @param exam     quiz or test we are adding questions to
     * @param question the question to be added
     */
    private void addQuestion(Exam exam, Question question) {
        exam.getQuestions().add(question);
    }

    /**
     * removes a question from a quiz/test
     *
     * @param exam     quiz or test we are removing questions from
     * @param question the question to be removed
     */
    private void removeQuestion(Exam exam, Question question) {
        exam.getQuestions().remove(question);
    }

    /**
     *
     * @param topic topic of the question
     * @param type long/mc/tf
     * @param questionText the context of the question
     * @param answer the answer for the question
     * @param options only for mc questions. These are the different answers to choose from
     */
    private void createQuestion(Topic topic, String type, String questionText, Object answer, String... options) {
        Question newQuestion = QuestionFactory.createQuestion(type, questionText, answer, options);
    }

    private boolean removeQuestion(Question question) {
        // TODO Create removeQuestion() method
        return false;
    }

    private boolean editQuestion(Question question, String questionText, Object answer) {
        // TODO Create editQuestion() method
        return false;
    }

    private boolean gradeQuiz(Student student, Quiz quiz, double grade) {
        // TODO Create gradeQuiz() method
        return false;
    }

    private boolean gradeTest(Student student, Test test, double grade) {
        // TODO Create gradeTest() method
        return false;
    }
}
