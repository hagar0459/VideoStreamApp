package tobeapps.intigral.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by HP on 6/10/2018.
 */

public interface AllTeamResponse {
    @Headers("Cache-control: no-cache")
    @GET("sample.json")
    Call<TeamsMainResponseModel> getTeam();
}
