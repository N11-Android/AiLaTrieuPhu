package haui.android.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import haui.android.MainActivity;
import haui.android.R;


public class SettingsActivity extends Activity implements View.OnClickListener {
    private ToggleButton togMusic;
    private ToggleButton togSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        togMusic = ((ToggleButton)findViewById(R.id.tog_music));
        togSound = ((ToggleButton)findViewById(R.id.tog_sound));
        setBgTogMusic(MainActivity.getMusicPlayer().getStateMusic());
        setBgTogSound(MainActivity.getMusicPlayer().getStateSound());

        togMusic.setOnClickListener(this);
        togSound.setOnClickListener(this);
    }

    public void setBgTogMusic(boolean state){
        if(state){
            togMusic.setBackgroundResource(R.drawable.toggle_button_on);
            togMusic.setChecked(true);
        }else{
            togMusic.setBackgroundResource(R.drawable.toggle_button_off);
            togMusic.setChecked(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.getMusicPlayer().pauseBgMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.getMusicPlayer().resumeBgMusic();

    }

    public void setBgTogSound(boolean state){
        if(state){
            togSound.setBackgroundResource(R.drawable.toggle_button_on);
            togSound.setChecked(true);
        }else{
            togSound.setBackgroundResource(R.drawable.toggle_button_off);
            togSound.setChecked(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tog_music:
                if(togMusic.isChecked()){
                    togMusic.setBackgroundResource(R.drawable.toggle_button_on);
                    togMusic.setChecked(true);
                    MainActivity.getMusicPlayer().setStateMusic(true);
                    MainActivity.getMusicPlayer().playBgMusic(R.raw.bgmusic);
                }else{
                    togMusic.setBackgroundResource(R.drawable.toggle_button_off);
                    togMusic.setChecked(false);
                    MainActivity.getMusicPlayer().stopBgMusic();
                }
                break;
            case R.id.tog_sound:
                setBgTogSound(togSound.isChecked());
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.getMusicPlayer().setting(togMusic.isChecked(), togSound.isChecked());

    }


}
