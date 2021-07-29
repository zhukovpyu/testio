package com.example.android.testio;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mPassedTests,mMadeTests,mTrys,mQuestions;
    private List<TrysObject> Trys = new ArrayList<>();
    private List<QuestionsObject> Questions = new ArrayList<>();
    private List<PassedTestObject> PassedTests = new ArrayList<>();
    private List<MadeTestsObject> MadeTests = new ArrayList<>();
    private FirebaseUser user;
    FirebaseUser currentU = FirebaseAuth.getInstance().getCurrentUser();
    String uId = currentU.getUid();

    public interface DataStatus{
        void DataIsLoaded(List<PassedTestObject> PassedTests, List<String> keys);
        void DataIsLoaded2(List<MadeTestsObject> MadeTests, List<String> keys);
        void DataIsLoaded3(List<TrysObject> Trys, List<String> keys);
        void DataIsLoaded4(List<QuestionsObject> Questions, List<String> keys);
        void DataIsInserted();
        void DataIsUpdatad();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {

        //Переменные для чтение из базы данных Firebase
        mDatabase = FirebaseDatabase.getInstance();
        mPassedTests = mDatabase.getReference().child("Users").child(uId).child("PassedTest");
        mMadeTests = mDatabase.getReference().child("Users").child(uId).child("UserMadeTest");
        if(TestIdx.getTestId()!=null){
        mTrys = mDatabase.getReference().child("Tests").child(TestIdx.getTestId()).child("Trys");
        mQuestions = mDatabase.getReference().child("Tests").child(TestIdx.getTestId()).child("Questions");}
    }

    //Чтение попыток прохождения теста
    public void readTrys(final DataStatus dataStatus){

        mTrys.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Trys.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode :  dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    TrysObject xTrys = keyNode.getValue(TrysObject.class);
                    Trys.add(xTrys);
                }
                dataStatus.DataIsLoaded3(Trys,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Чтение вопросов теста
    public void readQuestions(final DataStatus dataStatus){

        mQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Questions.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode :  dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    QuestionsObject xQuestion = keyNode.getValue(QuestionsObject.class);
                    Questions.add(xQuestion);
                }
                dataStatus.DataIsLoaded4(Questions,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //Чтение пройденных тестов
    public void readPassedTests(final DataStatus dataStatus){

        mPassedTests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            PassedTests.clear();
            List<String> keys = new ArrayList<>();
            for(DataSnapshot keyNode :  dataSnapshot.getChildren()){
                keys.add(keyNode.getKey());
                PassedTestObject PassedTest = keyNode.getValue(PassedTestObject.class);
                PassedTests.add(PassedTest);
            }
            dataStatus.DataIsLoaded(PassedTests,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //Чтение созданных тестов
    public void readMadeTests(final DataStatus dataStatus){

        mMadeTests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MadeTests.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode :  dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    MadeTestsObject MadeTest = keyNode.getValue(MadeTestsObject.class);
                    MadeTests.add(MadeTest);
                }
                dataStatus.DataIsLoaded2(MadeTests,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
