package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//ПРОХОЖДЕНИЕ ТЕСТА
public class PassingTestActivity extends AppCompatActivity {


    private String Try;
    private FirebaseUser currentU;
    private int ifAnswered;
    private int mScore = 0;
    private int mScoreForAnswer;
    private int dada = 0;
    private int check =1;
    private int noOfQuestion;
    private String noOfQuestionStr;
    private String typeOfQuestion;
    private String testIdx;
    private String qType0,qType1,qType2,qType3,qType4,qType5;
    private DatabaseReference qType1Quest;
    private DatabaseReference qType2Quest;
    private DatabaseReference qType3Quest;
    private DatabaseReference qType5Quest;
    FirebaseAuth mAuth;

    private String xyz,uId;

    private String aOfW;

    private TextView nameOfTest;
    private TextView questionText;

    private RadioGroup rdGroupOne;
    private RadioButton varOneTypeZero;
    private RadioButton varTwoTypeZero;
    private RadioButton varThreeTypeZero;
    private RadioButton varFourTypeZero;

    private String testCreatorStr;

    private CheckBox varOneTypeOne;
    private CheckBox varTwoTypeOne;
    private CheckBox varThreeTypeOne;
    private CheckBox varFourTypeOne;

    private TextView numberOfWords;
    private EditText answerText;

    private EditText answerNumber;

    private TextView questionText1;
    private TextView questionText2;
    private TextView questionText3;
    private TextView questionText4;

    private RadioButton yes;
    private RadioButton no;

    private String answerTypeZero,answer1TypeOne,answer2TypeOne,answerTypeTwo,answerTypeThree,answerTypeFive;
    private String qtype4ans1,qtype4ans2,qtype4ans3,qtype4ans4;
    private String[] anssFor4Type;

    List<String> forAdapt;

    ArrayAdapter<String> adapter;

    private Button  nextQuestion;

    private View qTypeZero,qTypeOne,qTypeTwo,qTypeThree,qTypeFour,qTypeFivex;


    private DatabaseReference mDatabase,nameOft,qType,qTypev2,qType0Quest,qType0var1,qType0var2,qType0var3,qType0var4,qType1var1,qType1var2,qType1var3,qType1var4,qType2var1,qType41Quest,qType42Quest,qType43Quest,qType44Quest
            ,qType4var1,qType4var2,qType4var3,qType4var4,answerType0,answer1Type1,answer2Type1,answerType2,answerType3,answerType5,scoreForRightAnswer,qExists,testCreator;

    public PassingTestActivity()  {
    }


    @Override
    //Инициализация макета
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_test_activity);

        noOfQuestion = 1;
        noOfQuestionStr = ""+noOfQuestion;
        testIdx = getIntent().getExtras().getString("testIdString");

        qTypeZero = (View) findViewById(R.id.q_type_0);
        qTypeOne = (View) findViewById(R.id.q_type_1);
        qTypeTwo = (View) findViewById(R.id.q_type_2);
        qTypeThree = (View) findViewById(R.id.q_type_3);
        qTypeFour = (View) findViewById(R.id.q_type_4);
        qTypeFivex = (View) findViewById(R.id.q_type_5_);

        qType0 = "0";
        qType1 = "1";
        qType2 = "2";
        qType3 = "3";
        qType4 = "4";
        qType5 = "5";

        aOfW = "Слов в ответе";

        nextQuestion=(Button)findViewById(R.id.next_question_);

        nameOfTest = (TextView)findViewById(R.id.name_of_test);
        questionText = (TextView)findViewById(R.id.question_text);

        rdGroupOne = (RadioGroup)findViewById(R.id.rd1);
        varOneTypeZero = (RadioButton)findViewById(R.id.var1qtype0);
        varTwoTypeZero = (RadioButton)findViewById(R.id.var2qtype0);
        varThreeTypeZero = (RadioButton)findViewById(R.id.var3qtype0);
        varFourTypeZero = (RadioButton)findViewById(R.id.var4qtype0);

        varOneTypeOne = (CheckBox)findViewById(R.id.var1type1);
        varTwoTypeOne = (CheckBox)findViewById(R.id.var2type1);
        varThreeTypeOne = (CheckBox)findViewById(R.id.var3type1);
        varFourTypeOne = (CheckBox)findViewById(R.id.var4type1);

        numberOfWords = (TextView)findViewById(R.id.amount_of_words_in_answer);
        answerText = (EditText)findViewById(R.id.answer_text);

        answerNumber = (EditText)findViewById(R.id.answer_number);

        questionText1=(TextView)findViewById(R.id.question_text1);
        //final Spinner spinnerOne = (Spinner)findViewById(R.id.q_spinner1);
        questionText2=(TextView)findViewById(R.id.question_text2);
        //final Spinner  spinnerTwo = (Spinner)findViewById(R.id.q_spinner2);
        questionText3=(TextView)findViewById(R.id.question_text3);
        //final Spinner  spinnerThree = (Spinner)findViewById(R.id.q_spinner3);
        questionText4=(TextView)findViewById(R.id.question_text4);
        //final Spinner  spinnerFour = (Spinner)findViewById(R.id.q_spinner4);

        yes = (RadioButton)findViewById(R.id.yes);
        no = (RadioButton)findViewById(R.id.no);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        nameOft = mDatabase.child("Tests").child(testIdx).child("NameOfTest");

        final Spinner spinnerOne = (Spinner) findViewById(R.id.q_spinner1);
        final Spinner spinnerTwo = (Spinner) findViewById(R.id.q_spinner2);
        final Spinner spinnerThree = (Spinner) findViewById(R.id.q_spinner3);
        final Spinner spinnerFour = (Spinner) findViewById(R.id.q_spinner4);

        forAdapt = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, forAdapt);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currentU = FirebaseAuth.getInstance().getCurrentUser();

        spinnerOne.setPrompt("Выберите вариант:");
        spinnerTwo.setPrompt("Выберите вариант:");
        spinnerThree.setPrompt("Выберите вариант:");
        spinnerFour.setPrompt("Выберите вариант:");
        final String checkStr = ""+check;
        noOfQuestion=1;
        updateQuestion();


        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score(xyz);
                int noOfNextQ = noOfQuestion+1;
                    qExists = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfNextQ);

                    qExists.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                nextQuestion.setText("Далее");
                                noOfQuestion=noOfQuestion+1;
                                updateQuestion();
                                if(dada == 1){
                                    adapter.notifyDataSetChanged();
                                    spinnerOne.setAdapter(adapter);
                                    spinnerTwo.setAdapter(adapter);
                                    spinnerThree.setAdapter(adapter);
                                    spinnerFour.setAdapter(adapter);
                                }
                            }
                            else{
                                    testCreator = mDatabase.child("Tests").child(testIdx).child("TestCreator");
                                    testCreator.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        testCreatorStr = dataSnapshot.getValue().toString();
                                        currentU = FirebaseAuth.getInstance().getCurrentUser();
                                        uId = currentU.getUid();
                                        Try = getTestId2();
                                        mDatabase.child("Tests").child(testIdx).child("Trys").child(Try).child("Passer").setValue(currentU.getDisplayName());
                                        mDatabase.child("Tests").child(testIdx).child("Trys").child(Try).child("Score").setValue(""+mScore);
                                        mDatabase.child("Users").child(uId).child("PassedTest").child(testIdx).child("TestId").setValue(testIdx);
                                        mDatabase.child("Users").child(uId).child("PassedTest").child(testIdx).child("FinalScore").setValue(""+mScore);
                                        mDatabase.child("Users").child(uId).child("PassedTest").child(testIdx).child("NameOfTest").setValue(nameOfTest.getText());
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                Intent intent = new Intent(PassingTestActivity.this, PassedActivity.class);
                                String mScoreStr = ""+mScore;
                                intent.putExtra("Score", mScoreStr);
                                startActivity(intent);

                        }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
        });
    }

    private void updateQuestion(){


        nameOft.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String y = dataSnapshot.getValue().toString();
                nameOfTest.setText(y);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        qType = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("TypeOfQuestion");

        qType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xyz =  dataSnapshot.getValue().toString();
                typeOfQuestion = xyz;
                setLayout(xyz);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //nextQuestion.setText(answerTypeZero+"\n"+mScoreForAnswer);

    }

    private void setLayout(String z){


        if (Integer.valueOf(z) == Integer.valueOf(qType0)){

            dada=0;

            qType0Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("QuestionText");

            qType0Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            qType0var1 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("Variant1");

            qType0var1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var1text = dataSnapshot.getValue().toString();
                    varOneTypeZero.setText(var1text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType0var2 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("Variant2");

            qType0var2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var2text = dataSnapshot.getValue().toString();
                    varTwoTypeZero.setText(var2text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType0var3 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("Variant3");

            qType0var3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var3text = dataSnapshot.getValue().toString();
                    varThreeTypeZero.setText(var3text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType0var4 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("Variant4");

            qType0var4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var4text = dataSnapshot.getValue().toString();
                    varFourTypeZero.setText(var4text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qTypeZero.setVisibility(View.VISIBLE);
            qTypeOne.setVisibility(View.GONE);
            qTypeTwo.setVisibility(View.GONE);
            qTypeThree.setVisibility(View.GONE);
            qTypeFour.setVisibility(View.GONE);
            qTypeFivex.setVisibility(View.GONE);
        }


        else if(Integer.valueOf(z) == Integer.valueOf(qType1)){

            dada=0;

            qType1Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("QuestionText");

            qType1Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType1var1 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Variant1");

            qType1var1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var1text = dataSnapshot.getValue().toString();
                    varOneTypeOne.setText(var1text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType1var2 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Variant2");

            qType1var2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var2text = dataSnapshot.getValue().toString();
                    varTwoTypeOne.setText(var2text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType1var3 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Variant3");

            qType1var3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var3text = dataSnapshot.getValue().toString();
                    varThreeTypeOne.setText(var3text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType1var4 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Variant4");

            qType1var4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var4text = dataSnapshot.getValue().toString();
                    varFourTypeOne.setText(var4text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qTypeZero.setVisibility(View.GONE);
            qTypeOne.setVisibility(View.VISIBLE);
            qTypeTwo.setVisibility(View.GONE);
            qTypeThree.setVisibility(View.GONE);
            qTypeFour.setVisibility(View.GONE);
            qTypeFivex.setVisibility(View.GONE);
        }
        else if(Integer.valueOf(z) == Integer.valueOf(qType2)){

            dada=0;

            qType2Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("QuestionText");

            qType2Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType2var1 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("AmountOfWords");

            qType2var1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String var1text = dataSnapshot.getValue().toString();
                    numberOfWords.setText(var1text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qTypeZero.setVisibility(View.GONE);
            qTypeOne.setVisibility(View.GONE);
            qTypeTwo.setVisibility(View.VISIBLE);
            qTypeThree.setVisibility(View.GONE);
            qTypeFour.setVisibility(View.GONE);
            qTypeFivex.setVisibility(View.GONE);
        }

        else if(Integer.valueOf(z) == Integer.valueOf(qType4)){

            dada=1;

            adapter.clear();

            qType41Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("QuestionText1");

            qType41Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText1.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType42Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Question1Text2");

            qType42Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText2.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType43Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Question1Text3");

            qType43Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText3.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType44Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Question1Text4");

            qType44Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText4.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType4var1 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer1");

            qType4var1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    qtype4ans1 = qText;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            qType4var2 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer2");

            qType4var2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    qtype4ans2 = qText;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType4var3 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer3");

            qType4var3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    qtype4ans3 = qText;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qType4var4 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer4");

            qType4var4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    qtype4ans4 = qText;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            adapter.add(qtype4ans1);
            adapter.add(qtype4ans2);
            adapter.add(qtype4ans3);
            adapter.add(qtype4ans4);



            qTypeZero.setVisibility(View.GONE);
            qTypeOne.setVisibility(View.GONE);
            qTypeTwo.setVisibility(View.GONE);
            qTypeThree.setVisibility(View.GONE);
            qTypeFour.setVisibility(View.VISIBLE);
            qTypeFivex.setVisibility(View.GONE);

        }
        else if(Integer.valueOf(z) == Integer.valueOf(qType3)){

            dada=0;

            qType3Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("QuestionText");

            qType3Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            qTypeZero.setVisibility(View.GONE);
            qTypeOne.setVisibility(View.GONE);
            qTypeTwo.setVisibility(View.GONE);
            qTypeThree.setVisibility(View.VISIBLE);
            qTypeFour.setVisibility(View.GONE);
            qTypeFivex.setVisibility(View.GONE);


        }
        else if(Integer.valueOf(z).equals(Integer.valueOf(qType5))) {

            dada=0;

            qType5Quest = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("QuestionText");

            qType5Quest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String qText = dataSnapshot.getValue().toString();
                    questionText.setText(qText);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            qTypeZero.setVisibility(View.GONE);
            qTypeOne.setVisibility(View.GONE);
            qTypeTwo.setVisibility(View.GONE);
            qTypeThree.setVisibility(View.GONE);
            qTypeFour.setVisibility(View.GONE);
            qTypeFivex.setVisibility(View.VISIBLE);
        }
        else {
            nextQuestion.setText("Не понял");
        }
    }

    public void score(String z){

        if(Integer.valueOf(z) == Integer.valueOf(qType0)){
            answerType0 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("Answer");

            answerType0.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    answerTypeZero = dataSnapshot.getValue().toString();

                    scoreForRightAnswer =  mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("AmountOfPoints");

                    scoreForRightAnswer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //String u = dataSnapshot.getValue().toString();
                            mScoreForAnswer = Integer.valueOf(dataSnapshot.getValue().toString());

                            if(varOneTypeZero.isChecked()||varTwoTypeZero.isChecked()||varThreeTypeZero.isChecked()||varFourTypeZero.isChecked()){



                                if (varOneTypeZero.isChecked()) {
                                    if ((varOneTypeZero.getText().equals(answerTypeZero))) {
                                    mScore = mScore + mScoreForAnswer;
                                    ifAnswered=1;}
                                    else{ifAnswered =1;}
                                }
                                else if (varTwoTypeZero.isChecked()){
                                    if ((varTwoTypeZero.getText().equals(answerTypeZero))) {
                                        mScore=mScore+mScoreForAnswer;
                                    ifAnswered=1;}else{ifAnswered =1;}
                                }
                                else if (varThreeTypeZero.isChecked()){
                                    if ((varTwoTypeZero.getText().equals(answerTypeZero))){
                                        mScore=mScore+mScoreForAnswer;
                                        nextQuestion.setText(mScore);
                                    ifAnswered=1;}else{ifAnswered =1;}
                                }
                                else if (varFourTypeZero.isChecked()){
                                    if ((varTwoTypeZero.getText().equals(answerTypeZero))){
                                        mScore=mScore+mScoreForAnswer;
                                        ifAnswered=1;}else{ifAnswered =1;}

                                }



                            }
                            else if(!varOneTypeZero.isChecked() &&!varTwoTypeZero.isChecked()&&!varThreeTypeZero.isChecked()&&!varFourTypeZero.isChecked()) {
                                ifAnswered=2;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else if (Integer.valueOf(z) == Integer.valueOf(qType1)){
            answer1Type1 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("Answer1");

            answer1Type1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    answer1TypeOne = dataSnapshot.getValue().toString();

                    scoreForRightAnswer =  mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("AmountOfPoints");

                    scoreForRightAnswer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //String u = dataSnapshot.getValue().toString();
                            mScoreForAnswer = Integer.valueOf(dataSnapshot.getValue().toString());

                            answer2Type1 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer2");

                            answer2Type1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    answer2TypeOne = dataSnapshot.getValue().toString();

                                    if((!varOneTypeOne.isChecked()&&!varTwoTypeOne.isChecked()&&!varThreeTypeOne.isChecked()&&!varFourTypeOne.isChecked())||
                                            (varOneTypeOne.isChecked()&&!varTwoTypeOne.isChecked()&&!varThreeTypeOne.isChecked()&&!varFourTypeOne.isChecked())||
                                            (!varOneTypeOne.isChecked()&&varTwoTypeOne.isChecked()&&!varThreeTypeOne.isChecked()&&!varFourTypeOne.isChecked())||
                                            (!varOneTypeOne.isChecked()&&!varTwoTypeOne.isChecked()&&varThreeTypeOne.isChecked()&&!varFourTypeOne.isChecked())||
                                            (!varOneTypeOne.isChecked()&&!varTwoTypeOne.isChecked()&&!varThreeTypeOne.isChecked()&&varFourTypeOne.isChecked()))
                                    {
                                        ifAnswered = 3;
                                    }
                                    else if (varOneTypeOne.isChecked()&&varTwoTypeOne.isChecked()){
                                        ifAnswered=1;
                                        if((varOneTypeOne.getText().equals(answer1TypeOne)&&varTwoTypeOne.getText().equals(answer2TypeOne))||(varOneTypeOne.getText().equals(answer2TypeOne)&&varTwoTypeOne.getText().equals(answer1TypeOne))){
                                            mScore=mScore+mScoreForAnswer;
                                        }
                                        else{ifAnswered =1;}
                                    }
                                    else if (varOneTypeOne.isChecked()&&varThreeTypeOne.isChecked()){
                                        ifAnswered=1;
                                        if((varOneTypeOne.getText().equals(answer1TypeOne)&&varThreeTypeOne.getText().equals(answer2TypeOne))||(varOneTypeOne.getText().equals(answer2TypeOne)&&varThreeTypeOne.getText().equals(answer1TypeOne))){
                                            mScore=mScore+mScoreForAnswer;
                                        }
                                        else{ifAnswered =1;}

                                    }
                                    else if (varOneTypeOne.isChecked()&&varFourTypeOne.isChecked()){
                                        ifAnswered=1;
                                        if((varOneTypeOne.getText().equals(answer1TypeOne)&&varFourTypeOne.getText().equals(answer2TypeOne))||(varOneTypeOne.getText().equals(answer2TypeOne)&&varFourTypeOne.getText().equals(answer1TypeOne))){
                                            mScore=mScore+mScoreForAnswer;
                                        }
                                        else{ifAnswered =1;}
                                    }
                                    else if (varTwoTypeOne.isChecked()&&varThreeTypeOne.isChecked()){
                                        ifAnswered=1;
                                        if((varTwoTypeOne.getText().equals(answer1TypeOne)&&varThreeTypeOne.getText().equals(answer2TypeOne))||(varTwoTypeOne.getText().equals(answer2TypeOne)&&varThreeTypeOne.getText().equals(answer1TypeOne))){
                                            mScore=mScore+mScoreForAnswer;
                                        }
                                        else{ifAnswered =1;}
                                    }
                                    else if (varTwoTypeOne.isChecked()&&varFourTypeOne.isChecked()){
                                        ifAnswered=1;
                                        if((varTwoTypeOne.getText().equals(answer1TypeOne)&&varFourTypeOne.getText().equals(answer2TypeOne))||(varTwoTypeOne.getText().equals(answer2TypeOne)&&varFourTypeOne.getText().equals(answer1TypeOne))){
                                            mScore=mScore+mScoreForAnswer;
                                        }
                                        else{ifAnswered =1;}

                                    }
                                    else if (varThreeTypeOne.isChecked()&&varFourTypeOne.isChecked()){
                                        ifAnswered=1;
                                        if((varThreeTypeOne.getText().equals(answer1TypeOne)&&varFourTypeOne.getText().equals(answer2TypeOne))||(varThreeTypeOne.getText().equals(answer2TypeOne)&&varFourTypeOne.getText().equals(answer1TypeOne))){
                                            mScore=mScore+mScoreForAnswer;
                                        }
                                        else{ifAnswered =1;}

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        else if(Integer.valueOf(z) == Integer.valueOf(qType2)){

            answerType2 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer");

            answerType2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    answerTypeTwo = dataSnapshot.getValue().toString();

                    scoreForRightAnswer =  mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("AmountOfPoints");

                    scoreForRightAnswer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mScoreForAnswer = Integer.valueOf(dataSnapshot.getValue().toString());

                            if(answerText.getText().toString().equals("")){

                                ifAnswered = 2;
                                }
                                else {
                                if(answerText.getText().toString().equalsIgnoreCase(answerTypeTwo) ){
                                ifAnswered =1;
                                mScore=mScore+mScoreForAnswer;
                                    }
                                else{ifAnswered =1;}
                                }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        else if(Integer.valueOf(z) == Integer.valueOf(qType3)){

            answerType3 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer");

            answerType3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    answerTypeThree = dataSnapshot.getValue().toString();

                    scoreForRightAnswer =  mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("AmountOfPoints");

                    scoreForRightAnswer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mScoreForAnswer = Integer.valueOf(dataSnapshot.getValue().toString());

                            if(answerNumber.getText().toString().equals("")){

                                ifAnswered = 2;
                            }
                            else {
                                if(answerNumber.getText().toString().equals(answerTypeThree)){
                                    ifAnswered =1;
                                    mScore=mScore+mScoreForAnswer;
                                }
                                else{ifAnswered =1;}
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        else if(Integer.valueOf(z) == Integer.valueOf(qType5)){

            answerType5 = mDatabase.child("Tests").child(testIdx).child("Questions").child("Question"+noOfQuestion).child("Answer");

            answerType5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    answerTypeFive = dataSnapshot.getValue().toString();

                    scoreForRightAnswer =  mDatabase.child("Tests").child(testIdx).child("Questions").child("Question" + noOfQuestion).child("AmountOfPoints");

                    scoreForRightAnswer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mScoreForAnswer = Integer.valueOf(dataSnapshot.getValue().toString());
                            //остановился тут!!
                            //остановился тут!!
                            //остановился тут!!
                            //остановился тут!!
                            if(yes.isChecked()){
                                String uit =("1");
                                if(uit.equals(answerTypeFive))
                                {
                                ifAnswered =1;
                                mScore=mScore+mScoreForAnswer;}
                                else{ifAnswered =1;}
                            }
                            else if (no.isChecked()) {
                                String ttt = "0";
                                if(ttt.equals(answerTypeFive)){
                                ifAnswered =1;
                                mScore=mScore+mScoreForAnswer;}
                                else{ifAnswered =1;}
                            }
                            else if (!yes.isChecked()&&!no.isChecked()){
                                ifAnswered = 2;
                            }

                            else{
                                ifAnswered=1;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }
    protected String getTestId2() {
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
