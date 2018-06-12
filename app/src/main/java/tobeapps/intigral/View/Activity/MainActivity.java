package tobeapps.intigral.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jkb.slidemenu.SlideMenuLayout;

import tobeapps.intigral.BaseActivity;
import tobeapps.intigral.R;
import tobeapps.intigral.View.Fragment.MediaFragment;

/**
 * Created by HP on 6/10/2018.
 */

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mtToolbarUpdateSliderTxt, mtToolbarUpdateVideoPlayerTxt;
    private SlideMenuLayout slideMenuLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);
        mToolbar = findViewById(R.id.tool_bar);
        mtToolbarUpdateSliderTxt = mToolbar.findViewById(R.id.txt_update_slider_state);
        slideMenuLayout = findViewById(R.id.mainSlideMenu);
        mtToolbarUpdateSliderTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenuLayout.toggleLeftSlide();
                slideMenuLayout.toggleRightSlide();

            }
        });

        mtToolbarUpdateVideoPlayerTxt = mToolbar.findViewById(R.id.txt_update_full_media_player);
        mtToolbarUpdateVideoPlayerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaFragment fragment = (MediaFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);//if you specify your fragment in xml
                fragment.exitFullscreen();
            }
        });
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(mToolbar);

    }



    @Override
    public void onBackPressed() {
        if (slideMenuLayout.isLeftSlideOpen() || slideMenuLayout.isRightSlideOpen()) {
            slideMenuLayout.closeLeftSlide();
            slideMenuLayout.closeRightSlide();
        } else {
            super.onBackPressed();
        }
    }



}
