package tobeapps.intigral.Core;

import android.content.Context;

import java.util.List;

import tobeapps.intigral.Model.TeamPlayerModel;


/**
 * Created by HP on 6/10/2018.
 */

public interface GetDataContract {
    interface View {
        void onGetDataSuccess(List<TeamPlayerModel> homeList, List<TeamPlayerModel> awayList);

        void onGetDataFailure(String message);
    }

    interface Presenter {
        void getDataFromURL(Context context, String url);
    }

    interface Interactor {
        void initRetrofitCall(Context context, String url);

    }

    interface onGetDataListener {
        void onSuccess(List<TeamPlayerModel> homeList, List<TeamPlayerModel> awayList);

        void onFailure(String message);
    }
}
