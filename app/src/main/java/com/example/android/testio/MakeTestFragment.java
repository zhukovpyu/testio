package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
//Фрагмент сделать тест
public class MakeTestFragment extends Fragment{

    private RecyclerView mRecyclerView2;
    FirebaseUser currentU = FirebaseAuth.getInstance().getCurrentUser();
    String uId = currentU.getUid();

    public MakeTestFragment() {
    }
    @Override
    //Инициализация макета
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_test, null);

        mRecyclerView2 = (RecyclerView) v.findViewById(R.id.recyclerview_madetests);
        if(uId!=null){
            new FirebaseDatabaseHelper().readMadeTests(new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void DataIsLoaded(List<PassedTestObject> PassedTests, List<String> keys) {
                }

                @Override
                public void DataIsLoaded3(List<TrysObject> Trys, List<String> keys) {

                }

                @Override
                public void DataIsLoaded4(List<QuestionsObject> Questions, List<String> keys) {

                }

                @Override
                public void DataIsLoaded2(List<MadeTestsObject> MadeTests, List<String> keys) {
                    new RecyclerView_Config().setConfig2(mRecyclerView2,getActivity(), MadeTests,keys);
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

        Button button = (Button) v.findViewById(R.id.make_test_button);
        button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeTestActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
