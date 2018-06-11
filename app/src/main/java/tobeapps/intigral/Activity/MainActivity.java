package tobeapps.intigral.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import tobeapps.intigral.Adapter.TeamAdapter;
import tobeapps.intigral.Core.GetDataContract;
import tobeapps.intigral.Core.Presenter;
import tobeapps.intigral.Model.Player;
import tobeapps.intigral.R;

/**
 * Created by HP on 6/10/2018.
 */

public class MainActivity extends AppCompatActivity implements GetDataContract.View {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    TeamAdapter homePlayersAdapter;
    TeamAdapter awayPlayersAdapter;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getApplicationContext(), getResources().getString(R.string.base_url));
        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onGetDataSuccess(List<Player> homeTeamPlayers, List<Player> awayTeamPlayers) {
        homePlayersAdapter = new TeamAdapter(getApplicationContext(), homeTeamPlayers);
        awayPlayersAdapter = new TeamAdapter(getApplicationContext(), awayTeamPlayers);
        recyclerView.setAdapter(homePlayersAdapter);

    }

    @Override
    public void onGetDataFailure(String msg) {
        Log.d("Status", msg);
    }
}
