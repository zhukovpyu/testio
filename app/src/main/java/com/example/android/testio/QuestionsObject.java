package com.example.android.testio;

//Класс объекта для списка вопросов теста
public class QuestionsObject {

    private String AmountOfPoints;
    private String QuestionText;
    private String Answer;

    public QuestionsObject() {
    }

    public QuestionsObject(String amountOfPoints, String questionText, String answer) {
        AmountOfPoints =amountOfPoints;
        QuestionText =questionText;
        Answer = answer;
    }

    public String getAmountOfPoints() {
        return AmountOfPoints;
    }

    public void setAmountOfPoints(String amountOfPoints) {
        AmountOfPoints = amountOfPoints;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
