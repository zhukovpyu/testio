package com.example.android.testio;

//Чтение/запись идентификатора теста
public class TestIdx {

    private static String TestId;

    public TestIdx(){}

    public TestIdx(String testId) {
        TestId = testId;
    }

    public static String getTestId() {
        return TestId;
    }

    public static void setTestId(String testId) {
        TestId = testId;
    }
}
