package com.example.android.testio;


//Класс объекта для списка попыток прохождения
public class TrysObject {

        private String Passer;
        private String Score;

        public TrysObject() {
        }

        public TrysObject(String passer, String score) {
            Passer = passer;
            Score = score;
        }

    public String getPasser() {
        return Passer;
    }

    public void setPasser(String passer) {
        Passer = passer;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}
