package tobeapps.intigral.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tobeapps.intigral.Model.Player;
import tobeapps.intigral.R;


/**
 * Created by HP on 6/10/2018.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {
    private Context context;
    private List<Player> list = new ArrayList<>();

    public TeamAdapter(Context context, List<Player> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TeamAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(TeamAdapter.MyViewHolder holder, int position) {
        holder.tvPlayerName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.tv_palayer_name);
        }
    }
}
