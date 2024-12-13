package org.example.finalproject.controller;

import org.example.finalproject.model.*;
import org.example.finalproject.model.factory.QuestionFactory;
import org.example.finalproject.model.singleton.QuizSystem;

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
    public void addStudent(Student student, Group group) {
        group.getStudents().add(student);
        student.getGroups().add(group);
    }

    /**
     * Creates a new group and adds it to the list of groups in Teacher class
     *
     * @param topic the topic of the new group
     */
    public void createGroup(Topic topic) {
        Group group = new Group(topic);
        teacher.getGroups().add(group);
    }

    /**
     * Creates a new quiz and adds it to the topic's list of quizzes
     *
     * @param topic The topic of the quiz
     */
    public void createQuiz(Topic topic) {
        Quiz quiz = new Quiz();
        topic.getQuizzes().add(quiz);
    }

    /**
     * Creates a new test and adds it to the topic's list of tests
     *
     * @param topic The topic of the test
     */
    public void createTest(Topic topic) {
        Test test = new Test();
        topic.getTests().add(test);
    }

    /**
     * adds a question to a quiz/test
     *
     * @param exam     quiz or test we are adding questions to
     * @param question the question to be added
     */
    public void addQuestion(Exam exam, Question question) {
        exam.getQuestions().add(question);
    }

    /**
     * removes a question from a quiz/test
     *
     * @param exam     quiz or test we are removing questions from
     * @param question the question to be removed
     */
    public void removeQuestion(Exam exam, Question question) {
        exam.getQuestions().remove(question);
    }

    /**
     * Creates a question using QuestionFactory and adds it to the Topic
     * @param topic topic of the question
     * @param type long/mc/tf
     * @param questionText the context of the question
     * @param answer the answer for the question
     * @param options only for mc questions. These are the different answers to choose from
     */
    public void createQuestion(Topic topic, String type, String questionText, Object answer, String... options) {
        Question newQuestion = QuestionFactory.createQuestion(type, questionText, answer, options);
        topic.getQuestions().add(newQuestion);
    }

    /**
     * Removes the question from the topic
     * @param topic topic of the question
     * @param question question input
     */
    public void deleteQuestion(Topic topic, Question question) {
        topic.getQuestions().remove(question);
    }

    /**
     * Edits the question, answer and the options if they are given
     * @param question Question to edit
     * @param questionText String input
     * @param answer String input
     * @param options String[] input
     */
    public void editQuestion(Question question, String questionText, Object answer, String... options) {
        question.setQuestion(questionText);
        question.setAnswer(answer);
        if (question.getClass().getSimpleName().equalsIgnoreCase("mcquestion")) {
            ((McQuestion) question).setOptions(options);
        }
    }

    /**
     * Sets the grade of a test
     * @param test Test input
     * @param grade Grade input
     */
    public void gradeTest(Test test, Grade grade) {
        test.getGrades().add(grade);
    }
}
