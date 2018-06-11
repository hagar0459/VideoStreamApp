package tobeapps.intigral.Core;

import android.content.Context;

import java.util.List;

import tobeapps.intigral.Model.Player;


/**
 * Created by HP on 6/10/2018.
 */

public class Presenter implements GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private GetDataContract.View mGetDataView;
    private Intractor mIntractor;

    public Presenter(GetDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mIntractor = new Intractor(this);
    }

    @Override
    public void getDataFromURL(Context context, String url) {
        mIntractor.initRetrofitCall(context, url);
    }

    @Override
    public void onSuccess(List<Player> homeList, List<Player> awayList) {
        mGetDataView.onGetDataSuccess(homeList, awayList);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }
}
