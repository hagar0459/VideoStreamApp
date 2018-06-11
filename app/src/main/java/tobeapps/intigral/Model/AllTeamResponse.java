package tobeapps.intigral.Model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HP on 6/10/2018.
 */

public interface AllTeamResponse {
    @GET("sample.json")
    Call<Response> getTeam();
}
