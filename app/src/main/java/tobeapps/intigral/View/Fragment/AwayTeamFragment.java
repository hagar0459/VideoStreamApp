package tobeapps.intigral.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

import java.util.List;

import tobeapps.intigral.Adapter.TeamAdapter;
import tobeapps.intigral.Core.GetDataContract;
import tobeapps.intigral.Core.TeamPresenter;
import tobeapps.intigral.Model.TeamPlayerModel;
import tobeapps.intigral.R;
import tobeapps.intigral.VideoStramApplication;


public class AwayTeamFragment extends ListFragment implements GetDataContract.View {


    private boolean isMobile;
    private TeamPresenter mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isMobile = VideoStramApplication.getOrientation(getActivity());
        if (!isMobile) {
            mPresenter = new TeamPresenter(this);
            mPresenter.getDataFromURL(getActivity().getApplicationContext(), getResources().getString(R.string.base_url));
        }
    }

    public void updateList(List<TeamPlayerModel> awayPlayersList) {
        if (awayPlayersList.size() == 0) {
            setEmptyText(getResources().getString(R.string.msg_no_players));

        } else {
            setListAdapter(new TeamAdapter(getActivity(), awayPlayersList));
        }
    }


    @Override
    public void onGetDataSuccess(List<TeamPlayerModel> homeList, List<TeamPlayerModel> awayList) {
        if (!isMobile) {

            updateList(awayList);
        }
    }

    @Override
    public void onGetDataFailure(String message) {
        if (!isMobile) {

            Log.d("away list fail", message);
        }
    }
}
