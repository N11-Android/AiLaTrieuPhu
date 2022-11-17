package haui.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

import haui.android.model.User;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> user_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //user_list = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        helper.insertData();
        try {
            helper.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        user_list = helper.getHighscoreList();
//
//        for(int i  = 0; i < user_list.size(); i++){
//            System.out.println(user_list.get(i).toString());
//        }
//        System.out.println("OK!");
    }
}