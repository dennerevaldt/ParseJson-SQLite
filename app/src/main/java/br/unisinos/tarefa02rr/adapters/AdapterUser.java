package br.unisinos.tarefa02rr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.unisinos.tarefa02rr.R;
import br.unisinos.tarefa02rr.models.User;

/**
 * Created by dennerevaldtmachado on 06/07/16.
 */
public class AdapterUser extends BaseAdapter {
    private ArrayList<User> userArrayList;
    private Context context;

    public AdapterUser(ArrayList<User> userArrayList, Context context) {
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item_user, null);

        if (convertView != null){
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView username = (TextView) convertView.findViewById(R.id.username);
            TextView email = (TextView) convertView.findViewById(R.id.email);

            User veiculo = userArrayList.get(position);
            name.setText(veiculo.getName());
            username.setText(veiculo.getUsername());
            email.setText(veiculo.getEmail());
        }

        return convertView;
    }
}
