package com.example.android.testio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class TestInformationActivity extends AppCompatActivity {

    private String TestId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_information_activity);

        TestId = getIntent().getStringExtra("TestId");
        TestIdx.setTestId(TestId);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        FragAdapter2 adapter1 = new FragAdapter2(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }


}
