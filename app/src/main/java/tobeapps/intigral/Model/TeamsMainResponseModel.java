package tobeapps.intigral.Model;

import android.support.annotation.NonNull;

/**
 * Created by HP on 6/10/2018.
 */

public class TeamsMainResponseModel {
    @NonNull
    private TeamsSecondaryModel Lineups;

    public TeamsSecondaryModel getLineups() {
        return Lineups;
    }

    public void setLineups(TeamsSecondaryModel lineups) {
        Lineups = lineups;
    }
}
