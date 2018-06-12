package tobeapps.intigral;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.devbrackets.android.exomedia.ExoMedia;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;

import okhttp3.OkHttpClient;
import tobeapps.intigral.Core.MediaPlaylistManager;

public class VideoStramApplication extends Application {
    @Nullable
    private MediaPlaylistManager playlistManager;

    public static boolean getOrientation(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        boolean orientation = true;
        orientation = !(screenInches > 6.0);

        return orientation;
    }

    public static void showToast(Context activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        playlistManager = new MediaPlaylistManager(this);
        LeakCanary.install(this);
        configureExoMedia();
    }

    @Nullable
    public MediaPlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    private void configureExoMedia() {
        // Registers the media sources to use the OkHttp client instead of the standard Apache one
        // Note: the OkHttpDataSourceFactory can be found in the ExoPlayer extension library `extension-okhttp`
        ExoMedia.setDataSourceFactoryProvider(new ExoMedia.DataSourceFactoryProvider() {
            @Nullable
            private CacheDataSourceFactory instance;

            @NonNull
            @Override
            public DataSource.Factory provide(@NonNull String userAgent, @Nullable TransferListener<? super DataSource> listener) {
                if (instance == null) {
                    // Updates the network data source to use the OKHttp implementation
                    DataSource.Factory upstreamFactory = new OkHttpDataSourceFactory(new OkHttpClient(), userAgent, listener);

                    // Adds a cache around the upstreamFactory
                    Cache cache = new SimpleCache(new File(getCacheDir(), "ExoMediaCache"), new LeastRecentlyUsedCacheEvictor(50 * 1024 * 1024));
                    instance = new CacheDataSourceFactory(cache, upstreamFactory, CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);
                }

                return instance;
            }
        });
    }
}
