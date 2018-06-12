package tobeapps.intigral.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 6/10/2018.
 */

public class TeamPlayerModel {
    @NonNull
    @SerializedName("Order")
    private int Order;

    @NonNull
    @SerializedName("StartInField")
    private boolean StartInField;

    @NonNull
    @SerializedName("Role")
    private String Role;

    @NonNull
    @SerializedName("IsCaptain")
    private boolean IsCaptain;

    @NonNull
    @SerializedName("JerseyNumber")
    private String JerseyNumber;

    @NonNull
    @SerializedName("Id")
    private int Id;

    @NonNull
    @SerializedName("Name")
    private String Name;

    @NonNull
    @SerializedName("XCoordinate")
    private int XCoordinate;

    @NonNull
    @SerializedName("YCoordinate")
    private int YCoordinate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @NonNull
    public String getRole() {
        return Role;
    }
}
