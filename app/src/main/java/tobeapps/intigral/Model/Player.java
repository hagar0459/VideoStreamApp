package tobeapps.intigral.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 6/10/2018.
 */

public class Player {
    @SerializedName("Order")
    private int Order;
    @SerializedName("StartInField")
    private boolean StartInField;
    @SerializedName("Role")
    private String Role;
    @SerializedName("IsCaptain")
    private boolean IsCaptain;
    @SerializedName("JerseyNumber")
    private String JerseyNumber;
    @SerializedName("Id")
    private int Id;
    @SerializedName("Name")
    private String Name;
    @SerializedName("XCoordinate")
    private int XCoordinate;
    @SerializedName("YCoordinate")
    private int YCoordinate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
