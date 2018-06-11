package tobeapps.intigral.Core;

import android.content.Context;

import java.util.List;

import tobeapps.intigral.Model.TeamPlayerModel;


/**
 * Created by HP on 6/10/2018.
 */

public class TeamPresenter implements GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private GetDataContract.View mGetDataView;
    private TeamIntractor mIntractor;

    public TeamPresenter(GetDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mIntractor = new TeamIntractor(this);
    }

    @Override
    public void getDataFromURL(Context context, String url) {
        mIntractor.initRetrofitCall(context, url);
    }

    @Override
    public void onSuccess(List<TeamPlayerModel> homeList, List<TeamPlayerModel> awayList) {
        mGetDataView.onGetDataSuccess(homeList, awayList);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }
}
