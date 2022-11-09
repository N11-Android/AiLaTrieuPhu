package haui.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout_money);

//        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
//        helper.insertData();
//        try {
//            helper.copyDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}