package com.example.restarent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button login;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        reg = (Button) findViewById(R.id.reg);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                //если логин и пароль совпадает с модераторами то открывается активность модера
                //если логин совпадает с пользователем, то открываем активность пользователя
                Intent intent1 = new Intent(this, GuestActivity.class);
                startActivity(intent1);
                break;
            case R.id.reg:
                Intent intent2 = new Intent(this, Activity_Reg.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}