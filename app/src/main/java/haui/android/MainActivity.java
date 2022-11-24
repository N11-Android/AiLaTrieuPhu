package haui.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;
import java.util.ArrayList;

import haui.android.model.User;

import haui.android.layout.HighScore;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> user_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
//        helper.insertData();
//        try {
//            helper.copyDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}