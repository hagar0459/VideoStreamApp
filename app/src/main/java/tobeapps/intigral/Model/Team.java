package tobeapps.intigral.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 6/10/2018.
 */

public class Team {
    @SerializedName("Players")
    private List<Player> Players;

    public List<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(List<Player> players) {
        Players = players;
    }
}
