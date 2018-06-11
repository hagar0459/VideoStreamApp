package tobeapps.intigral.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 6/10/2018.
 */

public class TeamModel {
    @NonNull
    @SerializedName("Players")
    private List<TeamPlayerModel> Players;

    public List<TeamPlayerModel> getPlayers() {
        return Players;
    }

    public void setPlayers(List<TeamPlayerModel> players) {
        Players = players;
    }
}
