package tobeapps.intigral.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.VideoControlsSeekListener;
import com.devbrackets.android.exomedia.listener.VideoControlsVisibilityListener;
import com.devbrackets.android.exomedia.ui.widget.VideoControls;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.util.LinkedList;
import java.util.List;

import tobeapps.intigral.App;
import tobeapps.intigral.Core.MediaPlaylistManager;
import tobeapps.intigral.Core.MediaVideoApi;
import tobeapps.intigral.Model.MediaItem;
import tobeapps.intigral.Model.MediaSamples;
import tobeapps.intigral.R;

/**
 * Created by HP on 6/11/2018.
 */

public class MediaFragment extends Fragment implements VideoControlsSeekListener {

    protected MediaVideoApi videoApi;
    protected VideoView videoView;
    private FullScreenListener fullScreenListener = new FullScreenListener();
    private MediaPlaylistManager playlistManager;

    @Override
    public boolean onSeekStarted() {
        playlistManager.invokeSeekStarted();
        return true;
    }

    @Override
    public boolean onSeekEnded(long seekTime) {
        playlistManager.invokeSeekEnded(seekTime);
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_media_player, container, false);
        videoView =view.findViewById(R.id.video_play_activity_video_view);
        init();
        initUiFlags();

        if (videoView.getVideoControls() != null) {
            videoView.getVideoControls().setVisibilityListener(new ControlsVisibilityListener());
        }
        return view;

    }


    protected void init() {
        setupPlaylistManager();
        videoView.setHandleAudioFocus(true);
        videoView.getVideoControls().setSeekListener(this);

        videoApi = new MediaVideoApi(videoView);
        playlistManager.addVideoApi(videoApi);
        playlistManager.play(0, false);
    }

    /**
     * Retrieves the playlist instance and performs any generation
     * of content if it hasn't already been performed.
     */
    private void setupPlaylistManager() {
        playlistManager = ((App)getActivity().getApplicationContext()).getPlaylistManager();

        List<MediaItem> mediaItems = new LinkedList<>();
        for (MediaSamples.MediaSampleList sample : MediaSamples.getVideoSamples()) {
            MediaItem mediaItem = new MediaItem(sample, false);
            mediaItems.add(mediaItem);
        }

        playlistManager.setParameters(mediaItems, 0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        playlistManager.invokeStop();
        // Resets the flags
        View decorView = getActivity().getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    private void goFullscreen() {
        setUiFlags(true);
    }

    public void exitFullscreen() {
        try {
            setUiFlags(false);
            videoView.setMeasureBasedOnAspectRatioEnabled(true);
            videoView.setScaleType(ScaleType.NONE);
        } catch (Exception e) {

        }
    }

    /**
     * Correctly sets up the fullscreen flags to avoid popping when we switch
     * between fullscreen and not
     */
    private void initUiFlags() {
        View decorView = getActivity().getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(getStableUiFlags());
            decorView.setOnSystemUiVisibilityChangeListener(fullScreenListener);
        }
        //To enable resume after system interuption
        //TODO:test on real device
        videoView.setHandleAudioFocus(true);

        //To support full screen in all device
        videoView.setMeasureBasedOnAspectRatioEnabled(false);
        videoView.setScaleType(ScaleType.FIT_XY);
    }

    /**
     * Applies the correct flags to the windows decor view to enter
     * or exit fullscreen mode
     *
     * @param fullscreen True if entering fullscreen mode
     */
    private void setUiFlags(boolean fullscreen) {
        View decorView = getActivity().getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(fullscreen ? getFullscreenUiFlags() : getStableUiFlags());
        }

    }

    /**
     * Determines the appropriate fullscreen flags based on the
     * systems API version.
     *
     * @return The appropriate decor view flags to enter fullscreen mode when supported
     */
    private int getFullscreenUiFlags() {
        return View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    }

    private int getStableUiFlags() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    }

    @Override
    public void onStop() {
        super.onStop();
        playlistManager.removeVideoApi(videoApi);
        playlistManager.invokeStop();
    }

    /**
     * Listens to the system to determine when to show the default controls
     * for the {@link VideoView}
     */
    private class FullScreenListener implements View.OnSystemUiVisibilityChangeListener {
        private int lastVisibility = 0;

        @Override
        public void onSystemUiVisibilityChange(int visibility) {
            // NOTE: if the screen is double tapped in just the right way (or wrong way)
            // the SYSTEM_UI_FLAG_HIDE_NAVIGATION flag is dropped. Because of this we
            // no longer get notified of the temporary change when the screen is tapped
            // (i.e. the VideoControls get the touch event instead of the OS). So we store
            // the visibility off for use in the ControlsVisibilityListener for verification
            lastVisibility = visibility;
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                videoView.showControls();
            }
        }
    }

    /**
     * A Listener for the {@link VideoControls}
     * so that we can re-enter fullscreen mode when the controls are hidden.
     */
    private class ControlsVisibilityListener implements VideoControlsVisibilityListener {
        @Override
        public void onControlsShown() {
            if (fullScreenListener.lastVisibility != View.SYSTEM_UI_FLAG_VISIBLE) {
                exitFullscreen();
            }
        }

        @Override
        public void onControlsHidden() {
            goFullscreen();
        }
    }
}
