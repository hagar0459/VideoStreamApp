package tobeapps.intigral.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.List;

import tobeapps.intigral.Adapter.TeamAdapter;
import tobeapps.intigral.Model.TeamPlayerModel;


public class AwayTeamFragment extends ListFragment {



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void updateList(List<TeamPlayerModel> awayPlayersList) {
        setListAdapter(new TeamAdapter(getActivity(), awayPlayersList));
    }


}
