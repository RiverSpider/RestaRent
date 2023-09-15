package com.example.restarent;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.*;

public class Orders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        TextView ordersTextView = findViewById(R.id.ordersTextView);

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://ep-late-credit-31222695.eu-central-1.aws.neon.tech/neondb", "Rote-cloud", "zp4wkyljt8Ri");

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT tables.count_human, tables.name_human FROM tables JOIN admin ON tables.id_resto = admin.id_resto");

            StringBuilder orders = new StringBuilder();
            while (rs.next()) {
                int countHuman = rs.getInt("count_human");
                String nameHuman = rs.getString("name_human");
                orders.append(nameHuman).append(": ").append(countHuman).append(" мест\n");
            }

            rs.close();
            stmt.close();
            conn.close();

            ordersTextView.setText(orders.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}