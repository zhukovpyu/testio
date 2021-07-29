package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MakeTestActivity extends AppCompatActivity {

    private String[] qustionType = {"Множественный выбор","Выбор из нескольких правильных","Написание ответа(текст)","Написание ответа(число)","Сопоставление(Сделано, но не проверить()","Да/Нет"};
    private DatabaseReference myRef;
    private EditText nameOfTest;
    private String testId;//мб надо будет менять

    private int qN;

    private EditText questionText;
    private EditText var1;
    private EditText var2;
    private EditText var3;
    private EditText var4;
    private EditText correctAnswer;

    private EditText questionText2;
    private EditText var21;
    private EditText var22;
    private EditText var23;
    private EditText var24;
    private EditText correctAnswer21;
    private EditText correctAnswer22;

    private EditText questionText3;
    private EditText amountOfWords;
    private EditText var31;

    private EditText questionText4;
    private EditText var41;

    private EditText questionText51;
    private EditText var51;
    private EditText questionText52;
    private EditText var52;
    private EditText questionText53;
    private EditText var53;
    private EditText questionText54;
    private EditText var54;

    private EditText questionText61;
    private RadioButton rbTrue;
    private RadioButton rbFalse;



    String numberOfQuestion;
    private int qType;
    private EditText pointsForAnswer;



    @Override
    //Инициализация макета
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_test_activity);

        //Макеты для разных типов вопросов
        final View layout1 = (View) findViewById(R.id.MultipleChoiceOA);
        final View layout2 = (View) findViewById(R.id.MultipleChoiceNA);
        final View layout3 = (View) findViewById(R.id.TypingText);
        final View layout4 = (View) findViewById(R.id.TypingNumber);
        final View layout5 = (View) findViewById(R.id.Matching);
        final View layout6 = (View) findViewById(R.id.TrueFalse);

        myRef = FirebaseDatabase.getInstance().getReference();
        final Button nextQuestion = (Button) findViewById(R.id.next_question);
        Button submitTest =(Button) findViewById(R.id.submit_test);
        nameOfTest = (EditText)findViewById(R.id.name_of_test);
        //testId = (EditText)findViewById(R.id.test_id);

        questionText = (EditText)findViewById(R.id.text_of_question1);
        var1 = (EditText)findViewById(R.id.var11);
        var2 = (EditText)findViewById(R.id.var21);
        var3= (EditText)findViewById(R.id.var31);
        var4 = (EditText)findViewById(R.id.var41);
        correctAnswer= (EditText)findViewById(R.id.correct_answer);

        questionText2 = (EditText)findViewById(R.id.text_of_question2);
        var21 = (EditText)findViewById(R.id.var22);
        var22 = (EditText)findViewById(R.id.var23);
        var23= (EditText)findViewById(R.id.var24);
        var24 = (EditText)findViewById(R.id.var25);
        correctAnswer21= (EditText)findViewById(R.id.correct_answer21);
        correctAnswer22= (EditText)findViewById(R.id.correct_answer22);

        questionText3 = (EditText)findViewById(R.id.text_of_question3);
        amountOfWords = (EditText)findViewById(R.id.number_of_words);
        var31 = (EditText)findViewById(R.id.correct_answer3);

        questionText4 = (EditText)findViewById(R.id.text_of_question4);
        var41=(EditText)findViewById(R.id.correct_answer4);

        questionText51=(EditText)findViewById(R.id.text_of_question51);
        var51=(EditText)findViewById(R.id.correct_answer51);
        questionText52=(EditText)findViewById(R.id.text_of_question52);
        var52=(EditText)findViewById(R.id.correct_answer52);
        questionText53=(EditText)findViewById(R.id.text_of_question53);
        var53=(EditText)findViewById(R.id.correct_answer53);
        questionText54=(EditText)findViewById(R.id.text_of_question54);
        var54=(EditText)findViewById(R.id.correct_answer54);

        questionText61=(EditText)findViewById(R.id.text_of_question6);;
        rbTrue = (RadioButton)findViewById(R.id.radio_yes);
        rbFalse = (RadioButton)findViewById(R.id.radio_no);



        pointsForAnswer = (EditText)findViewById(R.id.points_for_answer);
        testId = getTestId();

        ArrayAdapter<String> questionTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qustionType);

        questionTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qustionType);

        questionTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spQuestionType = (Spinner) findViewById(R.id.q_spinner);

        spQuestionType.setAdapter(questionTypeAdapter);

        spQuestionType.setPrompt("Выберите тип вопроса:");

        spQuestionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                setLayout(position);

            }

            //Показ определенного вида вопроса, исходя из выбора пользователя
            private void setLayout(int qTypeNumber)
            {
                if(qTypeNumber==0){
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                    layout5.setVisibility(View.GONE);
                    layout6.setVisibility(View.GONE);
                    pointsForAnswer.setVisibility(View.VISIBLE);
                }
                else if(qTypeNumber==1){
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                    layout5.setVisibility(View.GONE);
                    layout6.setVisibility(View.GONE);
                    pointsForAnswer.setVisibility(View.VISIBLE);
                }
                else if(qTypeNumber==2){
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.VISIBLE);
                    layout4.setVisibility(View.GONE);
                    layout5.setVisibility(View.GONE);
                    layout6.setVisibility(View.GONE);
                    pointsForAnswer.setVisibility(View.VISIBLE);
                }
                else if(qTypeNumber==3){
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.VISIBLE);
                    layout5.setVisibility(View.GONE);
                    layout6.setVisibility(View.GONE);
                    pointsForAnswer.setVisibility(View.VISIBLE);
                }
                else if(qTypeNumber==4){
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                    layout5.setVisibility(View.VISIBLE);
                    layout6.setVisibility(View.GONE);
                    pointsForAnswer.setVisibility(View.VISIBLE);
                }
                else if(qTypeNumber==5) {
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                    layout5.setVisibility(View.GONE);
                    layout6.setVisibility(View.VISIBLE);
                    pointsForAnswer.setVisibility(View.VISIBLE);
                }
                else{layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    layout4.setVisibility(View.GONE);
                    layout5.setVisibility(View.GONE);
                    layout6.setVisibility(View.GONE);}
                    pointsForAnswer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        qN = 1;
        //Действия при нажатии на кнопку следюущий вопрос
        nextQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {


                int qTypeInHere = spQuestionType.getSelectedItemPosition();
                String qType= ""+qTypeInHere;

                //ОГРОМНЫЙ БЛОК ИФ ЕЛСЕ ,В КОТОРОМ РЕАЛИЗУЕТСЯ ЗАПИСЬ ВОПРОСА В БАЗУ ДАННЫХ И ТОАСТЫ ПРИ НЕПРАВИЛЬНОМ ЗАПОЛНЕНИЙ МАКЕТА ВОПРОСА
                if (qTypeInHere==0) {
                    if (var1.getText().toString().equals("") || var2.getText().toString().equals("")|| var3.getText().toString().equals("")|| var4.getText().toString().equals("") || questionText.getText().toString().equals("")||
                            correctAnswer.getText().toString().equals("") || pointsForAnswer.getText().toString().equals(""))
                    {
                        Toast.makeText(MakeTestActivity.this,"Заполните все поля!", Toast.LENGTH_LONG).show();
                    }
                    else if(qN==1 && nameOfTest.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Введите название теста!", Toast.LENGTH_LONG).show();
                    }
                    else if (!correctAnswer.getText().toString().equals(var1.getText().toString()) && !correctAnswer.getText().toString().equals(var2.getText().toString())
                            && !correctAnswer.getText().toString().equals(var3.getText().toString()) && !correctAnswer.getText().toString().equals(var4.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Ни один из вариантов ответа не совпадает с ответом!", Toast.LENGTH_LONG).show();
                    }
                    else if (var1.getText().toString().equals(var2.getText().toString())||var1.getText().toString().equals(var3.getText().toString())
                            ||var1.getText().toString().equals(var4.getText().toString())||var2.getText().toString().equals(var3.getText().toString())
                            ||var2.getText().toString().equals(var4.getText().toString())||var2.getText().toString().equals(var4.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Варианты ответа должы отличаться друг от друга!", Toast.LENGTH_LONG).show();
                    }
                    else if((correctAnswer.getText().toString().equals(var1.getText().toString()) && correctAnswer.getText().toString().equals(var2.getText().toString()))
                            ||(correctAnswer.getText().toString().equals(var1.getText().toString()) && correctAnswer.getText().toString().equals(var3.getText().toString()))
                            ||(correctAnswer.getText().toString().equals(var1.getText().toString()) && correctAnswer.getText().toString().equals(var4.getText().toString()))
                            ||(correctAnswer.getText().toString().equals(var2.getText().toString()) && correctAnswer.getText().toString().equals(var3.getText().toString()))
                            ||(correctAnswer.getText().toString().equals(var2.getText().toString()) && correctAnswer.getText().toString().equals(var4.getText().toString()))
                            |(correctAnswer.getText().toString().equals(var3.getText().toString()) && correctAnswer.getText().toString().equals(var4.getText().toString()))){
                        Toast.makeText(MakeTestActivity.this, "Несолько вариантов ответа совпадают с ответом!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (qN == 1) {
                            nameOfTest.setVisibility(View.GONE);
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant1").setValue(var1.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant2").setValue(var2.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant3").setValue(var3.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant4").setValue(var4.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue(correctAnswer.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;
                            questionText.setText("");
                            var1.setText("");
                            var2.setText("");
                            var3.setText("");
                            var4.setText("");
                            correctAnswer.setText("");
                            pointsForAnswer.setText("");


                        } else {
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant1").setValue(var1.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant2").setValue(var2.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant3").setValue(var3.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant4").setValue(var4.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue(correctAnswer.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;
                            questionText.setText("");
                            var1.setText("");
                            var2.setText("");
                            var3.setText("");
                            var4.setText("");
                            correctAnswer.setText("");
                            pointsForAnswer.setText("");
                        }
                    }
                }
                else if(qTypeInHere==1){
                    if (var21.getText().toString().equals("") || var22.getText().toString().equals("")|| var23.getText().toString().equals("")|| var24.getText().toString().equals("") || questionText2.getText().toString().equals("")||
                            correctAnswer21.getText().toString().equals("") ||correctAnswer22.getText().toString().equals("")|| pointsForAnswer.getText().toString().equals(""))
                    {
                        Toast.makeText(MakeTestActivity.this, "Заполните все поля!", Toast.LENGTH_LONG).show();
                    }
                    else if(qN==1 && nameOfTest.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Введите название теста!", Toast.LENGTH_LONG).show();
                    }
                    else if (!correctAnswer21.getText().toString().equals(var21.getText().toString()) && !correctAnswer21.getText().toString().equals(var22.getText().toString())
                            && !correctAnswer21.getText().toString().equals(var23.getText().toString()) && !correctAnswer21.getText().toString().equals(var24.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Нет ответа для ответа №1!", Toast.LENGTH_LONG).show();
                    }
                    else if (!correctAnswer22.getText().toString().equals(var21.getText().toString()) && !correctAnswer22.getText().toString().equals(var22.getText().toString())
                            && !correctAnswer22.getText().toString().equals(var23.getText().toString()) && !correctAnswer22.getText().toString().equals(var24.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Нет ответа для ответа №1!", Toast.LENGTH_LONG).show();

                    }
                    else if (correctAnswer22.getText().toString().equals(correctAnswer21.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Ответы не должны совпадать", Toast.LENGTH_LONG).show();

                    }
                    else if((correctAnswer21.getText().toString().equals(var21.getText().toString()) && correctAnswer21.getText().toString().equals(var22.getText().toString()))
                            ||(correctAnswer21.getText().toString().equals(var21.getText().toString()) && correctAnswer21.getText().toString().equals(var23.getText().toString()))
                            ||(correctAnswer21.getText().toString().equals(var21.getText().toString()) && correctAnswer21.getText().toString().equals(var24.getText().toString()))
                            ||(correctAnswer21.getText().toString().equals(var22.getText().toString()) && correctAnswer21.getText().toString().equals(var23.getText().toString()))
                            ||(correctAnswer21.getText().toString().equals(var22.getText().toString()) && correctAnswer21.getText().toString().equals(var24.getText().toString()))
                            |(correctAnswer21.getText().toString().equals(var23.getText().toString()) && correctAnswer21.getText().toString().equals(var24.getText().toString()))){
                        Toast.makeText(MakeTestActivity.this, "Несолько вариантов ответа совпадают с ответом №1!", Toast.LENGTH_LONG).show();
                    }
                    else if (var21.getText().toString().equals(var22.getText().toString())||var21.getText().toString().equals(var23.getText().toString())
                            ||var21.getText().toString().equals(var24.getText().toString())||var22.getText().toString().equals(var23.getText().toString())
                            ||var22.getText().toString().equals(var24.getText().toString())||var22.getText().toString().equals(var24.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Варианты ответа должы отличаться друг от друга!", Toast.LENGTH_LONG).show();
                    }
                    else if((correctAnswer22.getText().toString().equals(var21.getText().toString()) && correctAnswer22.getText().toString().equals(var22.getText().toString()))
                            ||(correctAnswer22.getText().toString().equals(var21.getText().toString()) && correctAnswer22.getText().toString().equals(var23.getText().toString()))
                            ||(correctAnswer22.getText().toString().equals(var21.getText().toString()) && correctAnswer22.getText().toString().equals(var24.getText().toString()))
                            ||(correctAnswer22.getText().toString().equals(var22.getText().toString()) && correctAnswer22.getText().toString().equals(var23.getText().toString()))
                            ||(correctAnswer22.getText().toString().equals(var22.getText().toString()) && correctAnswer22.getText().toString().equals(var24.getText().toString()))
                            |(correctAnswer22.getText().toString().equals(var23.getText().toString()) && correctAnswer22.getText().toString().equals(var24.getText().toString()))){
                        Toast.makeText(MakeTestActivity.this, "Несолько вариантов ответа совпадают с ответом №2!", Toast.LENGTH_LONG).show();
                    }
                    else if(correctAnswer21.getText().toString().equals(correctAnswer22.getText().toString())){
                        Toast.makeText(MakeTestActivity.this, "Ответы должны отличаться!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (qN == 1) {
                            nameOfTest.setVisibility(View.GONE);
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText2.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant1").setValue(var21.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant2").setValue(var22.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant3").setValue(var23.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant4").setValue(var24.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(correctAnswer21.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer2").setValue(correctAnswer22.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText2.setText("");
                            var21.setText("");
                            var22.setText("");
                            var23.setText("");
                            var24.setText("");
                            correctAnswer21.setText("");
                            correctAnswer22.setText("");
                            pointsForAnswer.setText("");


                        } else {
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText2.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant1").setValue(var21.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant2").setValue(var22.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant3").setValue(var23.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Variant4").setValue(var24.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(correctAnswer21.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer2").setValue(correctAnswer22.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;
                            questionText2.setText("");
                            var21.setText("");
                            var22.setText("");
                            var23.setText("");
                            var24.setText("");
                            correctAnswer21.setText("");
                            correctAnswer22.setText("");
                            pointsForAnswer.setText("");
                        }
                    }
                }
                else if(qTypeInHere==2){
                    if (questionText3.getText().toString().equals("")) {
                        Toast.makeText(MakeTestActivity.this, "Введите вопрос!", Toast.LENGTH_LONG).show();
                    }
                    else if(var31.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Отсутствует овтет!", Toast.LENGTH_LONG).show();
                    }
                    else if(amountOfWords.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Введите кол-во слов в ответе", Toast.LENGTH_LONG).show();
                    }
                    else if(pointsForAnswer.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Введите кол-во баллов за овтет", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (qN == 1) {
                            nameOfTest.setVisibility(View.GONE);
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText3.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue(var31.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfWords").setValue(amountOfWords.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText3.setText("");
                            var31.setText("");
                            amountOfWords.setText("");
                            pointsForAnswer.setText("");


                        } else {
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText3.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue(var31.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfWords").setValue(amountOfWords.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText3.setText("");
                            var31.setText("");
                            amountOfWords.setText("");
                            pointsForAnswer.setText("");
                        }
                    }
                }
                else if(qTypeInHere==3){
                    if (questionText4.getText().toString().equals("")) {
                        Toast.makeText(MakeTestActivity.this, "Введите вопрос!", Toast.LENGTH_LONG).show();
                    }
                    else if(var41.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Отсутствует овтет!", Toast.LENGTH_LONG).show();
                    }
                    else if(pointsForAnswer.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Введите кол-во баллов за овтет", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (qN == 1) {
                            nameOfTest.setVisibility(View.GONE);
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText4.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue(var41.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText4.setText("");
                            var41.setText("");
                            pointsForAnswer.setText("");
                        }
                        else {
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText4.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue(var41.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText4.setText("");
                            var41.setText("");
                            pointsForAnswer.setText("");
                        }
                    }
                }
                else if(qTypeInHere==4){
                    if(var51.getText().toString().equals("") || var52.getText().toString().equals("")|| var53.getText().toString().equals("")|| var54.getText().toString().equals("") || questionText51.getText().toString().equals("")||
                            questionText52.getText().toString().equals("")|| pointsForAnswer.getText().toString().equals("")||
                            questionText53.getText().toString().equals("")||
                            questionText54.getText().toString().equals(""))
                    {
                        Toast.makeText(MakeTestActivity.this,"Заполните все поля!", Toast.LENGTH_LONG).show();
                    }
                    else if(questionText51.getText().toString().equals(questionText52.getText().toString())||
                            questionText51.getText().toString().equals(questionText53.getText().toString())||
                            questionText51.getText().toString().equals(questionText54.getText().toString())||
                            questionText52.getText().toString().equals(questionText53.getText().toString())||
                            questionText52.getText().toString().equals(questionText54.getText().toString())||
                            questionText53.getText().toString().equals(questionText54.getText().toString())){
                        Toast.makeText(MakeTestActivity.this,"Вопросы не должны совпадать!", Toast.LENGTH_LONG).show();
                    }
                    else if(var51.getText().toString().equals(var52.getText().toString())||
                            var51.getText().toString().equals(var53.getText().toString())||
                            var51.getText().toString().equals(var54.getText().toString())||
                            var52.getText().toString().equals(var53.getText().toString())||
                            var52.getText().toString().equals(var54.getText().toString())||
                            var53.getText().toString().equals(var54.getText().toString())){
                        Toast.makeText(MakeTestActivity.this,"Ответы не должны совпадать!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (qN == 1) {
                            nameOfTest.setVisibility(View.GONE);
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText1").setValue(questionText51.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Question1Text2").setValue(questionText52.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Question1Text3").setValue(questionText53.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Question1Text4").setValue(questionText54.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(var51.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(var52.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(var53.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(var54.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText51.setText("");
                            questionText52.setText("");
                            questionText53.setText("");
                            questionText54.setText("");
                            var51.setText("");
                            var52.setText("");
                            var53.setText("");
                            var54.setText("");
                            pointsForAnswer.setText("");
                        }
                        else {
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText1").setValue(questionText51.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Question1Text2").setValue(questionText52.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Question1Text3").setValue(questionText53.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Question1Text4").setValue(questionText54.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer1").setValue(var51.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer2").setValue(var52.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer3").setValue(var53.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer4").setValue(var54.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            qN++;

                            questionText51.setText("");
                            questionText52.setText("");
                            questionText53.setText("");
                            questionText54.setText("");
                            var51.setText("");
                            var52.setText("");
                            var53.setText("");
                            var54.setText("");
                            pointsForAnswer.setText("");
                        }
                    }
                }
                else if(qTypeInHere==5){
                    /*questionText61=(EditText)findViewById(R.id.text_of_question6);;
                    rbTrue = (RadioButton)findViewById(R.id.radio_yes);
                    rbFalse = (RadioButton)findViewById(R.id.radio_no);*/
                    if (questionText61.getText().toString().equals("")) {
                        Toast.makeText(MakeTestActivity.this, "Введите вопрос!", Toast.LENGTH_LONG).show();
                    }
                    else if(rbTrue.isChecked()==false&&rbFalse.isChecked()==false){
                        Toast.makeText(MakeTestActivity.this, "Выберите один из вариантов!", Toast.LENGTH_LONG).show();
                    }
                    else if(pointsForAnswer.getText().toString().equals("")){
                        Toast.makeText(MakeTestActivity.this, "Введите кол-во баллов за овтет", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (qN == 1) {
                            nameOfTest.setVisibility(View.GONE);
                            myRef.child("Tests").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText61.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            if(rbTrue.isChecked()){
                                myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue("1");
                            }
                            else{
                                myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue("0");
                            }
                            qN++;

                            questionText61.setText("");
                            rbTrue.setEnabled(false);
                            rbFalse.setEnabled(false);
                            pointsForAnswer.setText("");
                        }
                        else {
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("TypeOfQuestion").setValue(qType);
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("QuestionText").setValue(questionText61.getText().toString());
                            myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("AmountOfPoints").setValue(pointsForAnswer.getText().toString());
                            if(rbTrue.isChecked()){
                                myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue("1");
                            }
                            else{
                                myRef.child("Tests").child(testId).child("Questions").child("Question" + qN).child("Answer").setValue("0");
                            }
                            qN++;

                            questionText61.setText("");
                            rbTrue.setChecked(false);
                            rbFalse.setChecked(false);
                            pointsForAnswer.setText("");
                        }
                    }
                }

            }
        });

        //Действия при нажатий на кнопку "Опубликовать тест"
        submitTest.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(qN<=2){
                    Toast.makeText(MakeTestActivity.this, "Минимальное количество вопросов в тесте 3!", Toast.LENGTH_SHORT).show();
                }
                else{//ЗАПИСЬ ИНФОРМАЦИИ О ТЕСТЕ В БАЗУ ДАННЫХ
                FirebaseUser currentU = FirebaseAuth.getInstance().getCurrentUser();
                assert currentU != null;
                String uId = currentU.getUid();
                myRef.child("Tests").child(testId).child("NumberOfQuetions").setValue(qN);
                myRef.child("Tests").child(testId).child("TestCreator").setValue(uId);
                myRef.child("Users").child(uId).child("UserMadeTest").child(testId).child("TestId").setValue(testId);
                myRef.child("Users").child(uId).child("UserMadeTest").child(testId).child("NameOfTest").setValue(nameOfTest.getText().toString());
                Intent intent = new Intent(MakeTestActivity.this, TestInfActivity.class);
                intent.putExtra("testId", testId);
                intent.putExtra("nameOfTest", nameOfTest.getText().toString());
                startActivity(intent);}

            }
        });
    }

    //Генерация уникального идентификатора теста
    protected String getTestId() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // длина рандомной строки
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
}
