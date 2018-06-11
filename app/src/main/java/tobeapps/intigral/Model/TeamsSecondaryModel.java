package tobeapps.intigral.Model;

import android.support.annotation.NonNull;

/**
 * Created by HP on 6/10/2018.
 */

public class TeamsSecondaryModel {
    @NonNull
    private boolean Success;

    @NonNull
    private TeamsLineupListsModel Data;

    public TeamsLineupListsModel getData() {
        return Data;
    }

    public void setData(TeamsLineupListsModel data) {
        Data = data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }
}
