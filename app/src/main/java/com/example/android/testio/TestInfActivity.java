package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//Информация о тесте
public class TestInfActivity extends AppCompatActivity {

    TextView idOfTest;
    TextView nameOfTest;
    String x;
    String z;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_inf_activity);
        idOfTest = (TextView) findViewById(R.id.id_of_test);
        nameOfTest = (TextView)findViewById(R.id.name_of_test);
        x = getIntent().getStringExtra("testId");
        z = getIntent().getStringExtra("nameOfTest");
        nameOfTest.setText("Название теста:"+z);
        idOfTest.setText("Идентификатор теста: "+x);
        Button ok = (Button) findViewById(R.id.button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestInfActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TestInfActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
