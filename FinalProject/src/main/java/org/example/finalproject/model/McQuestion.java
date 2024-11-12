package org.example.finalproject.model;

public class McQuestion extends Question {
    public char answer;


    public McQuestion(String question) {
        super(question);
    }

    public McQuestion(String question, char answer) {
        super(question);
        this.answer = answer;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }
}
