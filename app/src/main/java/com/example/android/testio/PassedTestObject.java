package com.example.android.testio;

//Класс объекта для списка проиденных тестов
public class PassedTestObject {
    private String NameOfTest;
    private String TestId;
    private String FinalScore;

    public PassedTestObject() {
    }

    public PassedTestObject(String nameOfTest, String testId, String finalScore) {
        NameOfTest = nameOfTest;
        TestId = testId;
        FinalScore = finalScore;
    }

    public String getNameOfTest() {
        return NameOfTest;
    }

    public void setNameOfTest(String nameOfTest) {
        NameOfTest = nameOfTest;
    }

    public String getTestId() {
        return TestId;
    }

    public void setTestId(String testId) {
        TestId = testId;
    }

    public String getFinalScore() {
        return FinalScore;
    }

    public void setFinalScore(String finalScore) {
        FinalScore = finalScore;
    }
}
