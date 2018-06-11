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
import tobeapps.intigral.Model.Player;
import tobeapps.intigral.Model.Response;
import tobeapps.intigral.R;

/**
 * Created by HP on 6/10/2018.
 */

public class Intractor implements GetDataContract.Interactor {
    List<Player> awayTeam = new ArrayList<>();
    List<Player> homeTeam = new ArrayList<>();
    private GetDataContract.onGetDataListener mOnGetDatalistener;

    public Intractor(GetDataContract.onGetDataListener mOnGetDatalistener) {
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
        retrofit2.Call<Response> call = request.getTeam();
        call.enqueue(new retrofit2.Callback<Response>() {
            @Override
            public void onResponse(Call<Response> ResponseModel, retrofit2.Response<Response> response) {
                Response jsonResponse = response.body();
                if (jsonResponse.getLineups() != null && jsonResponse.getLineups().isSuccess()) {
                    if (jsonResponse.getLineups().getData() != null) {
                        if (jsonResponse.getLineups().getData().getHomeTeam() != null || jsonResponse.getLineups().getData().getAwayTeam() != null) {
                            if (jsonResponse.getLineups().getData().getHomeTeam() != null && jsonResponse.getLineups().getData().getHomeTeam().getPlayers() != null)
                                homeTeam = jsonResponse.getLineups().getData().getHomeTeam().getPlayers();
                            if (jsonResponse.getLineups().getData().getAwayTeam() != null && jsonResponse.getLineups().getData().getAwayTeam().getPlayers() != null)
                                awayTeam = jsonResponse.getLineups().getData().getAwayTeam().getPlayers();
                            mOnGetDatalistener.onSuccess(homeTeam, awayTeam);

                        } else {
                            mOnGetDatalistener.onFailure(context.getResources().getString(R.string.general_error_msg));

                        }


                    } else {
                        mOnGetDatalistener.onFailure(context.getResources().getString(R.string.general_error_msg));

                    }


                } else {
                    mOnGetDatalistener.onFailure(context.getResources().getString(R.string.general_error_msg));

                }

            }

            @Override
            public void onFailure(retrofit2.Call<Response> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
