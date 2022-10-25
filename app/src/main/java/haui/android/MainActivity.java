package haui.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper ALTP = new DatabaseHelper(MainActivity.this);
        ALTP.insertData();
        //MainActivity.this.deleteDatabase("game.db");
    }
}