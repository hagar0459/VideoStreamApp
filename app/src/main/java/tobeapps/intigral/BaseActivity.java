package tobeapps.intigral;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import tobeapps.intigral.Core.ConnectivityChangeReceiver;

/**
 * Created by HP on 6/12/2018.
 */

public class BaseActivity extends AppCompatActivity implements ConnectivityChangeReceiver.OnConnectivityChangedListener {

    private ConnectivityChangeReceiver connectivityChangeReceiver;

    public static int getOrientation(Context context) {
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
        int orientation = 0;
        if (screenInches > 6.0) {
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        } else
            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        return orientation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityChangeReceiver = new ConnectivityChangeReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(connectivityChangeReceiver, filter);
        if (getOrientation(this) == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    public void onConnectivityChanged(boolean isConnected) {
        if (!isConnected) {
            VideoStramApplication.showToast(getApplicationContext(), getResources().getString(R.string.msg_fail_network));
        } else {
            VideoStramApplication.showToast(getApplicationContext(), getResources().getString(R.string.msg_success_network));

        }
        // TODO handle connectivity change
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectivityChangeReceiver);
    }
}