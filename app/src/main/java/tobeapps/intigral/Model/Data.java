package tobeapps.intigral.Model;

/**
 * Created by HP on 6/10/2018.
 */

public class Data {
    private boolean Success;

    private TeamLineup Data;

    public TeamLineup getData() {
        return Data;
    }

    public void setData(TeamLineup data) {
        Data = data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }
}
