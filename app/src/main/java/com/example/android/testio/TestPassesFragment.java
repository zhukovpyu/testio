package com.example.android.testio;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

//Фрагмент прохождений теста
public class TestPassesFragment extends Fragment {

    private RecyclerView mRecyclerView2;
    FirebaseUser currentU = FirebaseAuth.getInstance().getCurrentUser();
    String uId = currentU.getUid();
    public TestPassesFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_passes_of_test, null);

            mRecyclerView2 = (RecyclerView) v.findViewById(R.id.lul1);
            if(uId!=null){
                new FirebaseDatabaseHelper().readTrys(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PassedTestObject> PassedTests, List<String> keys) {
                    }

                    @Override
                    public void DataIsLoaded3(List<TrysObject> Trys, List<String> keys) {
                        new RecyclerView_Config().setConfig3(mRecyclerView2,getActivity(), Trys,keys);
                    }

                    @Override
                    public void DataIsLoaded4(List<QuestionsObject> Questions, List<String> keys) {

                    }

                    @Override
                    public void DataIsLoaded2(List<MadeTestsObject> MadeTests, List<String> keys) {

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

        return v;
    }

}
