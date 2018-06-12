package tobeapps.intigral.Core.Service;


import android.support.annotation.NonNull;

import com.devbrackets.android.playlistcore.api.MediaPlayerApi;
import com.devbrackets.android.playlistcore.components.playlisthandler.DefaultPlaylistHandler;
import com.devbrackets.android.playlistcore.components.playlisthandler.PlaylistHandler;
import com.devbrackets.android.playlistcore.service.BasePlaylistService;

import tobeapps.intigral.Core.MediaAudioApi;
import tobeapps.intigral.Core.MediaPlaylistManager;
import tobeapps.intigral.Model.MediaItem;
import tobeapps.intigral.VideoStramApplication;

/**
 * A simple service that extends {@link BasePlaylistService} in order to provide
 * the application specific information required.
 */
public class MediaService extends BasePlaylistService<MediaItem, MediaPlaylistManager> {

    @Override
    public void onCreate() {
        super.onCreate();

        // Adds the audio player implementation, otherwise there's nothing to play media with
        getPlaylistManager().getMediaPlayers().add(new MediaAudioApi(getApplicationContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Releases and clears all the MediaPlayersMediaImageProvider
        for (MediaPlayerApi<MediaItem> player : getPlaylistManager().getMediaPlayers()) {
            player.release();
        }

        getPlaylistManager().getMediaPlayers().clear();
    }

    @NonNull
    @Override
    protected MediaPlaylistManager getPlaylistManager() {
        return ((VideoStramApplication) getApplicationContext()).getPlaylistManager();
    }

    @NonNull
    @Override
    public PlaylistHandler<MediaItem> newPlaylistHandler() {
        MediaImageProvider imageProvider = new MediaImageProvider(getApplicationContext(), new MediaImageProvider.OnImageUpdatedListener() {
            @Override
            public void onImageUpdated() {
                getPlaylistHandler().updateMediaControls();
            }
        });

        return new DefaultPlaylistHandler.Builder<>(
                getApplicationContext(),
                getClass(),
                getPlaylistManager(),
                imageProvider
        ).build();
    }
}