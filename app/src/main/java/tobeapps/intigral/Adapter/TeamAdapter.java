package tobeapps.intigral.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tobeapps.intigral.Model.TeamPlayerModel;
import tobeapps.intigral.R;


/**
 * Created by HP on 6/10/2018.
 */

public class TeamAdapter extends BaseAdapter {
    private Context context;
    private List<TeamPlayerModel> list = new ArrayList<>();

    public TeamAdapter(Context context, List<TeamPlayerModel> rowItem) {
        this.context = context;
        this.list = rowItem;

    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return list.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.team_adapter_list_item, null);
        }

        TextView tv_palayer_name = convertView.findViewById(R.id.tv_palayer_name);

        TeamPlayerModel row_pos = list.get(position);

        tv_palayer_name.setText(row_pos.getName() + "( " + row_pos.getRole() + " )");

        return convertView;

    }
}
