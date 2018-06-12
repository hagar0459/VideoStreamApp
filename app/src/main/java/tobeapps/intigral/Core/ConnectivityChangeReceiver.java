package tobeapps.intigral.Core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by HP on 6/12/2018.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    private OnConnectivityChangedListener listener;

    public ConnectivityChangeReceiver(OnConnectivityChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        NetworkInfo activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        listener.onConnectivityChanged(isConnected);
    }

    public interface OnConnectivityChangedListener {
        void onConnectivityChanged(boolean isConnected);
    }

}