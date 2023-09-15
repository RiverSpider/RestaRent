package com.example.restarent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button login;
    Button reg;
    EditText emailEditText;
    EditText passwordEditText;
    String  admin_id     ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        reg = (Button) findViewById(R.id.reg);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                try {
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://ep-late-credit-31222695.eu-central-1.aws.neon.tech/neondb", "Rote-cloud", "zp4wkyljt8Ri");

                    Statement stmt = conn.createStatement();

                    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'");

                    if (rs.next()) {
                        Intent intent = new Intent(this, GuestActivity.class);
                        startActivity(intent);
                    } else {
                        rs = stmt.executeQuery("SELECT * FROM admin WHERE email='" + email + "' AND password='" + password + "'");
                        if (rs.next()) { // Если админ найден в таблице admin
                            Intent intent = new Intent(this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                            LoginError myDialogFragment = new LoginError();
                            FragmentManager manager = getSupportFragmentManager();

                            FragmentTransaction transaction = manager.beginTransaction();
                            myDialogFragment.show(transaction, "dialog");
                        }
                    }

                    // Закрытие соединения и других ресурсов
                    rs.close();
                    stmt.close();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
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