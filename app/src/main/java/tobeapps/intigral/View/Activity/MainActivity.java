package tobeapps.intigral.View.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.VideoControlsSeekListener;
import com.devbrackets.android.exomedia.listener.VideoControlsVisibilityListener;
import com.devbrackets.android.exomedia.ui.widget.VideoControls;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.util.LinkedList;
import java.util.List;

import tobeapps.intigral.App;
import tobeapps.intigral.Core.MediaPlaylistManager;
import tobeapps.intigral.Model.MediaItem;
import tobeapps.intigral.Model.MediaSamples;
import tobeapps.intigral.Core.MediaVideoApi;
import tobeapps.intigral.Adapter.TeamAdapter;
import tobeapps.intigral.Core.GetDataContract;
import tobeapps.intigral.Core.TeamPresenter;
import tobeapps.intigral.Model.TeamPlayerModel;
import tobeapps.intigral.R;
import tobeapps.intigral.View.Fragment.MediaFragment;

/**
 * Created by HP on 6/10/2018.
 */

public class MainActivity extends AppCompatActivity implements GetDataContract.View {
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    TeamAdapter homePlayersAdapter;
    TeamAdapter awayPlayersAdapter;
    private TeamPresenter mPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Fragment detailFragment = (MediaFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
//        if(detailFragment == null){
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            detailFragment = new MediaFragment();
//            ft.replace(R.id.displayDetail, detailFragment, "Detail_Fragment1");
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();

        mPresenter = new TeamPresenter(this);
        mPresenter.getDataFromURL(getApplicationContext(), getResources().getString(R.string.base_url));
        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onGetDataSuccess(List<TeamPlayerModel> homeTeamPlayers, List<TeamPlayerModel> awayTeamPlayers) {
        homePlayersAdapter = new TeamAdapter(getApplicationContext(), homeTeamPlayers);
        awayPlayersAdapter = new TeamAdapter(getApplicationContext(), awayTeamPlayers);
        recyclerView.setAdapter(homePlayersAdapter);

    }

    @Override
    public void onGetDataFailure(String msg) {
        Log.d("Status", msg);
    }





}
