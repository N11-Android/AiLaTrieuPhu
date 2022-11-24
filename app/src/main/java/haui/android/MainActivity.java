package haui.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;

import haui.android.layout.HighScore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button_1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hsScreen = new Intent(MainActivity.this, HighScore.class);
                startActivity(hsScreen);
            }
        });

//        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
//        helper.insertData();
//        try {
//            helper.copyDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}