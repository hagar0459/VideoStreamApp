package tobeapps.intigral.Core;

import android.content.Context;

import java.util.List;

import tobeapps.intigral.Model.Player;


/**
 * Created by HP on 6/10/2018.
 */

public interface GetDataContract {
    interface View {
        void onGetDataSuccess(List<Player> homeList, List<Player> awayList);

        void onGetDataFailure(String message);
    }

    interface Presenter {
        void getDataFromURL(Context context, String url);
    }

    interface Interactor {
        void initRetrofitCall(Context context, String url);

    }

    interface onGetDataListener {
        void onSuccess(List<Player> homeList, List<Player> awayList);

        void onFailure(String message);
    }
}
