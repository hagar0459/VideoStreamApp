package tobeapps.intigral.Model;

import com.devbrackets.android.playlistcore.annotation.SupportedMediaType;
import com.devbrackets.android.playlistcore.api.PlaylistItem;

import tobeapps.intigral.Core.MediaPlaylistManager;

/**
 * A custom {@link PlaylistItem}
 * to hold the information pertaining to the audio and video items
 */
public class MediaItem implements PlaylistItem {
    private MediaSamples.MediaSampleList sample;
    boolean isAudio;

    public MediaItem(MediaSamples.MediaSampleList sample, boolean isAudio) {
        this.sample = sample;
        this.isAudio = isAudio;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public boolean getDownloaded() {
        return false;
    }

    @Override
    @SupportedMediaType
    public int getMediaType() {
        return isAudio ? MediaPlaylistManager.AUDIO : MediaPlaylistManager.VIDEO;
    }

    @Override
    public String getMediaUrl() {
        return sample.getMediaUrl();
    }

    @Override
    public String getDownloadedMediaUri() {
        return null;
    }

    @Override
    public String getThumbnailUrl() {
        return sample.getArtworkUrl();
    }

    @Override
    public String getArtworkUrl() {
        return sample.getArtworkUrl();
    }

    @Override
    public String getTitle() {
        return sample.getTitle();
    }

    @Override
    public String getAlbum() {
        return "PlaylistCore Demo";
    }

    @Override
    public String getArtist() {
        return "Unknown Artist";
    }
}