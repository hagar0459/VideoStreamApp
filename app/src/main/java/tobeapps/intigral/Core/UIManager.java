package tobeapps.intigral.Core;

import android.app.Activity;
import android.content.Intent;

import tobeapps.intigral.R;
import tobeapps.intigral.View.Activity.MainActivity;

/**
 * Created by HP on 6/12/2018.
 */


public class UIManager {
    /**
     * start  MAIN SCREEN
     *
     * @param activity
     * @param fullScreenMode boolean screen full mode if full it will start as landscape other it will start as portrait
     */

    public static void startVideoActivity(Activity activity, boolean fullScreenMode) {
        Intent mainIntent = new Intent(activity, MainActivity.class);
        mainIntent.putExtra("fullScreenMode", fullScreenMode);
        activity.startActivity(mainIntent);
        activity.finish();
        activity.overridePendingTransition(R.anim.enter, R.anim.exit);
    }


}
