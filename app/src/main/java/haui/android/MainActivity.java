package haui.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;
import java.util.ArrayList;

import haui.android.model.User;

import haui.android.layout.HighScore;

import haui.android.manager.MusicManager;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private static MusicManager musicPlayer;
    private ArrayList<User> user_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        musicPlayer = new MusicManager(this);
        setContentView(R.layout.activity_main);

//        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
//        helper.insertData();
//        try {
//            helper.copyDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static MusicManager getMusicPlayer(){
        return musicPlayer;
    }

    public static Context getContext() {
        return context;
    }
}