package tobeapps.intigral.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import tobeapps.intigral.Adapter.TeamAdapter;
import tobeapps.intigral.Core.GetDataContract;
import tobeapps.intigral.Model.TeamPlayerModel;


public class HomeTeamFragment extends ListFragment implements GetDataContract.View {
    private List<TeamPlayerModel> players;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onGetDataSuccess(List<TeamPlayerModel> homeList, List<TeamPlayerModel> awayList) {
        players = homeList;
        setListAdapter(new TeamAdapter(getActivity(), players));
    }

    @Override
    public void onGetDataFailure(String message) {
        players = new ArrayList<TeamPlayerModel>();
        setListAdapter(new TeamAdapter(getActivity(), players));
    }
}
