package com.example.restarent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.*;


public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DB_URL = "jdbc:postgresql://ep-late-credit-31222695.eu-central-1.aws.neon.tech/neondb";
    private static final String DB_USERNAME = "Rote-cloud";
    private static final String DB_PASSWORD = "zp4wkyljt8Ri";
    private int id_resto;
    Button addtable;
    Button orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin);

        addtable = (Button) findViewById(R.id.addtable);
        addtable.setOnClickListener(this);
        orders = (Button) findViewById(R.id.orders);
        orders.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addtable:
                showAddTableDialog();
                break;
            case R.id.orders:
                showAddTableDialog();
                break;
            default:
                break;
        }
    }

    private void showAddTableDialog() {
        // Создаем AlertDialog для ввода данных
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setTitle("Добавить столик");

        // Добавляем поля для ввода данных
        final EditText seatsEditText = new EditText(AdminActivity.this);
        seatsEditText.setHint("Количество мест");
        builder.setView(seatsEditText);

        final EditText descriptionEditText = new EditText(AdminActivity.this);
        descriptionEditText.setHint("Описание");
        builder.setView(descriptionEditText);

        // Добавляем кнопку "Добавить"
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Получаем id_resto зашедшего админа
                int id_resto = getCurrentAdminId();

                // Получаем данные из полей ввода
                int seats = Integer.parseInt(seatsEditText.getText().toString());
                String description = descriptionEditText.getText().toString();

                // Формируем запрос на добавление новой записи в таблицу tables
                String query = "INSERT INTO tables (id_resto, seats, description) VALUES (?, ?, ?)";
                try {
                    // Выполняем запрос на добавление новой записи в таблицу tables
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, id_resto);
                    statement.setInt(2, seats);
                    statement.setString(3, description);
                    statement.executeUpdate();

                    // Выводим сообщение об успешном добавлении записи
                    Toast.makeText(AdminActivity.this, "Столик успешно добавлен", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    // Выводим сообщение об ошибке при выполнении запроса
                    Toast.makeText(AdminActivity.this, "Ошибка при добавлении столика: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Отображаем AlertDialog
        builder.show();
    }

        private int getCurrentAdminId() {
        // Получаем id зашедшего админа из SharedPreferences
        SharedPreferences preferences = getSharedPreferences("admin", MODE_PRIVATE);
        int adminId = preferences.getInt("id", -1);

        return adminId;
        }

    private Connection connection;
    private void connectToDatabase() {
        try {
            // Загружаем драйвер для работы с базой данных
            Class.forName("com.mysql.jdbc.Driver");

            // Устанавливаем соединение с базой данных
            String url = "jdbc:postgresql://ep-late-credit-31222695.eu-central-1.aws.neon.tech/neondb";
            String user = "Rote-cloud";
            String password = "zp4wkyljt8Ri";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
