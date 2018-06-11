package tobeapps.intigral.Model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MediaSamples {
    @NonNull
    private static final List<MediaSampleList> videoSamples;

    static {
        //Video items
        videoSamples = new ArrayList<>();
        videoSamples.add(new MediaSampleList("HLS - ArtBeats", "http://intigralvod1-vh.akamaihd.net/i/3rdparty/Season2017_2018/10_12_2017_Hilal_fath/Highlights/high_,256,512,768,1200,.mp4.csmil/master.m3u8"));
    }



    @NonNull
    public static List<MediaSampleList> getVideoSamples() {
        return videoSamples;
    }

    /**
     * A container for the information associated with a
     * sample media item.
     */
    public static class MediaSampleList {
        @NonNull
        private String title;
        @NonNull
        private String mediaUrl;
        @Nullable
        private String artworkUrl;

        public MediaSampleList(@NonNull String title, @NonNull String mediaUrl) {
            this(title, mediaUrl, null);
        }

        public MediaSampleList(@NonNull String title, @NonNull String mediaUrl, @Nullable String artworkUrl) {
            this.title = title;
            this.mediaUrl = mediaUrl;
            this.artworkUrl = artworkUrl;
        }

        @NonNull
        public String getTitle() {
            return title;
        }

        @NonNull
        public String getMediaUrl() {
            return mediaUrl;
        }

        @Nullable
        public String getArtworkUrl() {
            return artworkUrl;
        }
    }
}
