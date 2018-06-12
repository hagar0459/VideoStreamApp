package tobeapps.intigral.Core;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tobeapps.intigral.Model.AllTeamResponse;
import tobeapps.intigral.Model.TeamPlayerModel;
import tobeapps.intigral.Model.TeamsMainResponseModel;
import tobeapps.intigral.R;

/**
 * Created by HP on 6/10/2018.
 */

public class TeamIntractor implements GetDataContract.Interactor {
    List<TeamPlayerModel> awayTeam = new ArrayList<>();
    List<TeamPlayerModel> homeTeam = new ArrayList<>();
    private GetDataContract.onGetDataListener mOnGetDatalistener;

    public TeamIntractor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;
    }

    @Override
    public void initRetrofitCall(final Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AllTeamResponse request = retrofit.create(AllTeamResponse.class);
        retrofit2.Call<TeamsMainResponseModel> call = request.getTeam();
        call.enqueue(new retrofit2.Callback<TeamsMainResponseModel>() {
            @Override
            public void onResponse(Call<TeamsMainResponseModel> ResponseModel, retrofit2.Response<TeamsMainResponseModel> response) {
                TeamsMainResponseModel jsonResponse = response.body();
                if (jsonResponse.getLineups() != null && jsonResponse.getLineups().isSuccess()) {
                    if (jsonResponse.getLineups().getData() != null) {
                        if (jsonResponse.getLineups().getData().getHomeTeam() != null || jsonResponse.getLineups().getData().getAwayTeam() != null) {
                            if (jsonResponse.getLineups().getData().getHomeTeam() != null && jsonResponse.getLineups().getData().getHomeTeam().getPlayers() != null)
                                homeTeam = jsonResponse.getLineups().getData().getHomeTeam().getPlayers();
                            if (jsonResponse.getLineups().getData().getAwayTeam() != null && jsonResponse.getLineups().getData().getAwayTeam().getPlayers() != null)
                                awayTeam = jsonResponse.getLineups().getData().getAwayTeam().getPlayers();
                            mOnGetDatalistener.onSuccess(homeTeam, awayTeam);

                        } else {
                            mOnGetDatalistener.onFailure(context.getResources().getString(R.string.msg_general_error));

                        }


                    } else {
                        mOnGetDatalistener.onFailure(context.getResources().getString(R.string.msg_general_error));

                    }


                } else {
                    mOnGetDatalistener.onFailure(context.getResources().getString(R.string.msg_general_error));

                }

            }

            @Override
            public void onFailure(retrofit2.Call<TeamsMainResponseModel> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
