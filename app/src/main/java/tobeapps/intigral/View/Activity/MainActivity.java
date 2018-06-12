package tobeapps.intigral.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jkb.slidemenu.SlideMenuLayout;

import tobeapps.intigral.BaseActivity;
import tobeapps.intigral.R;
import tobeapps.intigral.VideoStramApplication;
import tobeapps.intigral.View.Fragment.MediaFragment;

/**
 * Created by HP on 6/10/2018.
 */

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mtToolbarUpdateSliderTxt, mtToolbarUpdateVideoPlayerTxt;
    private SlideMenuLayout slideMenuLayout;
    private boolean fullScreenMode;
    private boolean isMobile;
    private MediaFragment mediaFragment;
    private boolean FullScreenMode = true;
    private boolean closedSideMenue = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        //get extra values from splash configuration
        if (extras != null) {
            fullScreenMode = extras.getBoolean("fullScreenMode", true);
        }
        //retrive value either true or false based on device dimentions as there are some devices with high density and are mobie not tabletes
        isMobile = VideoStramApplication.getOrientation(MainActivity.this);

        //updating orienation abased on full screen mode and dimentions of the device either tablet or mobile

        updateOrientation(fullScreenMode, isMobile);
        mediaFragment = (MediaFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);//if you specify your fragment in xml
        if (mToolbar != null) {
            mtToolbarUpdateSliderTxt = mToolbar.findViewById(R.id.txt_update_slider_state);
            mtToolbarUpdateSliderTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isMobile) {
                        slideMenuLayout.toggleRightSlide();

                    } else {
                        mediaFragment.togleBothSide(closedSideMenue);
                    }
                    if (closedSideMenue) {
                        mtToolbarUpdateSliderTxt.setText(getResources().getString(R.string.close_title));
                        closedSideMenue = false;
                    } else {
                        mtToolbarUpdateSliderTxt.setText(getResources().getString(R.string.open_title));
                        closedSideMenue = true;
                    }

                }
            });

            mtToolbarUpdateVideoPlayerTxt = mToolbar.findViewById(R.id.txt_update_full_media_player);
            mtToolbarUpdateVideoPlayerTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FullScreenMode) {
                        mtToolbarUpdateVideoPlayerTxt.setText(getResources().getString(R.string.close_fullscreen));
                        FullScreenMode = false;
                        mediaFragment.exitFullscreen();

                    } else {
                        mtToolbarUpdateVideoPlayerTxt.setText(getResources().getString(R.string.open_fullscreen));
                        FullScreenMode = true;
                        mediaFragment.initUiFlags();

                    }
                }
            });
        }
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(mToolbar);

    }

    /**
     * updateOrientation
     *
     * @param isMobile       based on device dimentions
     * @param fullScreenMode boolean screen full mode if full it will start as landscape other it will start as portrait
     */
    private void updateOrientation(boolean fullScreenMode, boolean isMobile) {
        if (fullScreenMode) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            if (isMobile) {
                setContentView(R.layout.activity_main_mobile);
                mToolbar = findViewById(R.id.tool_bar);

                initilizeMobileView();
            } else {
                setContentView(R.layout.activity_main_tablet);
                mToolbar = findViewById(R.id.tool_bar);

                initilizeTabletView();
            }


        } else {

//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            if (isMobile) {
                setContentView(R.layout.activity_main_mobile);
                mToolbar = findViewById(R.id.tool_bar);

                initilizeMobileView();

            } else {
                setContentView(R.layout.activity_main_tablet);
                mToolbar = findViewById(R.id.tool_bar);
                initilizeTabletView();
            }


        }
    }

    /**
     * initilizeMobile
     */
    private void initilizeMobileView() {
        slideMenuLayout = findViewById(R.id.mainSlideMenu);
        slideMenuLayout.setContentToggle(true);

    }

    private void initilizeTabletView() {

    }


    @Override
    public void onBackPressed() {
        if (isMobile) {
            if (slideMenuLayout.isLeftSlideOpen() || slideMenuLayout.isRightSlideOpen()) {
                slideMenuLayout.closeLeftSlide();
                slideMenuLayout.closeRightSlide();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        updateOrientation(fullScreenMode, isMobile);

    }
}
