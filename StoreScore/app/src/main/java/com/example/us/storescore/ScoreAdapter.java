package com.example.us.storescore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScoreAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private ArrayList<Profile> profileArrayList;

    public ScoreAdapter(MainActivity context, int layout, ArrayList<Profile> profileArrayList) {
        this.context = context;
        this.layout = layout;
        this.profileArrayList = profileArrayList;
    }

    @Override
    public int getCount() {
        return profileArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtUsername;
        TextView txtScore;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtUsername = convertView.findViewById(R.id.txtUsernameDisplay);
            holder.txtScore = convertView.findViewById(R.id.txtScoreDisplay);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Profile profile = profileArrayList.get(position);

        holder.txtUsername.setText(profile.getUsername().toString());
        holder.txtScore.setText(profile.getScore() + "");

        return convertView;
    }
}
