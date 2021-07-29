package com.example.android.testio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.IdpResponse;

import java.util.Arrays;
import java.util.List;

//Главный экран
public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 1;
    List<AuthUI.IdpConfig> providers;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    //Инициализация объектов при открытии приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance(); //Аутентификация
        user = FirebaseAuth.getInstance().getCurrentUser(); //Чтение пользователя
        //Способы входа
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );


        if(user==null){
            showSingInOptions();//Показ опций регистрации

                }
        else{
            //Инициализация активности после входа в аккаунт
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

            FragAdapter adapter = new FragAdapter(this, getSupportFragmentManager());

            viewPager.setAdapter(adapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

            tabLayout.setupWithViewPager(viewPager);
        }



    }

    //Показ опций регистрации
    private void showSingInOptions() {
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme)
                .setLogo(R.drawable.logo)
                .build(),
                MY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){ //Вход в аккаунт
                user = FirebaseAuth.getInstance().getCurrentUser();
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

                FragAdapter adapter = new FragAdapter(this, getSupportFragmentManager());

                viewPager.setAdapter(adapter);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

                tabLayout.setupWithViewPager(viewPager);
            }
            else{//Тоаст при ошибке входа
                Toast.makeText(this, ""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Инициализация меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sing_out,menu);//Опция выхода из аккаунта
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.sing_out_item){ //Выход  из аккаунта при нажатии
            FirebaseAuth.getInstance().signOut();
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else{
        return super.onOptionsItemSelected(item);
        }
        return true;
    }



}
