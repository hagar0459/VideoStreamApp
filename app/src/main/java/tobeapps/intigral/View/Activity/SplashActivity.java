package tobeapps.intigral.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tobeapps.intigral.Core.SharedPreferencesManager;
import tobeapps.intigral.Core.UIManager;
import tobeapps.intigral.R;
import tobeapps.intigral.VideoStramApplication;

/**
 * Created by HP on 6/12/2018.
 */

public class SplashActivity extends AppCompatActivity {
    EditText edit_seek_start_position;
    Button btn_done;
    CheckBox chk_video_title;
    private boolean fullScreenMode = true;
    private LinearLayout config_lay;
    private RelativeLayout label_lay;
    private TextView txt_config, txt_skip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        label_lay = findViewById(R.id.label_lay);
        config_lay = findViewById(R.id.config_lay);
        txt_skip = findViewById(R.id.txt_skip);
        txt_config = findViewById(R.id.txt_config);
        edit_seek_start_position = findViewById(R.id.edit_seek_start_position);
        edit_seek_start_position = findViewById(R.id.edit_seek_start_position);
        chk_video_title = findViewById(R.id.chk_video_title);
        chk_video_title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                fullScreenMode = ((CheckBox) v).isChecked();

            }
        });
        btn_done = findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edit_seek_start_position.getText().toString().trim())) {
                    saveConfiurations();
                    UIManager.startVideoActivity(SplashActivity.this, fullScreenMode);
                } else {
                    VideoStramApplication.showToast(SplashActivity.this, getResources().getString(R.string.msg_empty_config_error));
                }

            }
        });
        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.startVideoActivity(SplashActivity.this, fullScreenMode);

            }
        });
        final Animation bottomUp = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.bottom_up);
        final Animation upBottom = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.up_bottom);
        upBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                config_lay.startAnimation(upBottom);
                config_lay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        txt_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label_lay.startAnimation(bottomUp);
                label_lay.setVisibility(View.GONE);
            }
        });
    }


    /**
     * saveConfiurations
     * save source for video source and its sub title
     */
    private void saveConfiurations() {

        SharedPreferencesManager.getInstance(SplashActivity.this).setString(getResources().getString(R.string.pref_seek_position), edit_seek_start_position.getText().toString());

    }
}
