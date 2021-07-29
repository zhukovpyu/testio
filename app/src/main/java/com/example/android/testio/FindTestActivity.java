package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class FindTestActivity extends AppCompatActivity {
    private String testIdString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Загрузка макета
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_test_activity);

        //Чтение кнопки
        Button passThisTest = (Button) findViewById(R.id.pass_this_test);

        //Нажатие на кнопку
        passThisTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EditText testId = (EditText) findViewById(R.id.edit_id);
                    //Чтение эдиттектса
                    testIdString = testId.getText().toString();
                    if(testIdString!=null){
                    //Загрузка интента
                    Intent intent = new Intent(FindTestActivity.this, PassingTestActivity.class);
                    intent.putExtra("testIdString", testIdString);
                    startActivity(intent);}
                    //Тоаст
                    else{
                        Toast.makeText(FindTestActivity.this, "Введите идентификатор теста!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
