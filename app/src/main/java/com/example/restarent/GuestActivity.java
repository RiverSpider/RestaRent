package com.example.restarent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuestActivity extends AppCompatActivity implements View.OnClickListener{

    Button map;
    Button list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_guest);

        map = (Button) findViewById(R.id.map);
        list = (Button) findViewById(R.id.list);
        map.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.map:
                break;
            case R.id.list:
                Intent intent = new Intent(this, RestList.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}