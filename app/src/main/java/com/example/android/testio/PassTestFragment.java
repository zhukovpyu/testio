package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

//Фрагмент пройти тест
public class PassTestFragment extends Fragment {

    private RecyclerView mRecyclerView;
    FirebaseUser currentU = FirebaseAuth.getInstance().getCurrentUser();
    String uId = currentU.getUid();
    public PassTestFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pass_test, null);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_passedtests);
        if(uId!=null){
        new FirebaseDatabaseHelper().readPassedTests(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<PassedTestObject> PassedTests, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,getActivity(), PassedTests,keys);

            }

            @Override
            public void DataIsLoaded2(List<MadeTestsObject> MadeTests, List<String> keys) {

            }

            @Override
            public void DataIsLoaded3(List<TrysObject> Trys, List<String> keys) {

            }

            @Override
            public void DataIsLoaded4(List<QuestionsObject> Questions, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdatad() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });}

        Button button = (Button) v.findViewById(R.id.pass_test_button);
        button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), FindTestActivity.class);
                startActivity(intent2);
            }
        });
        return v;
    }

}
