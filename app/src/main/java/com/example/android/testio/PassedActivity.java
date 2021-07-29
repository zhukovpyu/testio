package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PassedActivity extends AppCompatActivity {
    TextView finalScore;
    Button oK;
    String x;
    @Override
    //Инициализация макета
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passed_test_activity);
        finalScore = (TextView)findViewById(R.id.points);
        oK = (Button)findViewById(R.id.btn_ok);
        oK.setText("Ок");
        oK.setOnClickListener(new View.OnClickListener() {

            //Возврат на главный экран
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
