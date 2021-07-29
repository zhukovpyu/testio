package com.example.android.testio;

//Класс объекта для списка созданных тестов
public class MadeTestsObject {
    private String NameOfTest;
    private String TestId;

    public MadeTestsObject() {
    }

    public MadeTestsObject(String nameOfTest, String testId) {
        NameOfTest = nameOfTest;
        TestId = testId;
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

}