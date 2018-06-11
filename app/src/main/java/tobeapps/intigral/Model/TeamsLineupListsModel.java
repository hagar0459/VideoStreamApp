package tobeapps.intigral.Model;

import android.support.annotation.NonNull;

/**
 * Created by HP on 6/10/2018.
 */

public class TeamsLineupListsModel {
    @NonNull
    private TeamModel HomeTeam;
    @NonNull
    private TeamModel AwayTeam;

    public TeamModel getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(TeamModel homeTeam) {
        HomeTeam = homeTeam;
    }

    public TeamModel getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(TeamModel awayTeam) {
        AwayTeam = awayTeam;
    }
}
