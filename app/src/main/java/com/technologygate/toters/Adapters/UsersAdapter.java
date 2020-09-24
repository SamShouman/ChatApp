package com.technologygate.toters.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.technologygate.toters.Models.GetTimesAgo;
import com.technologygate.toters.Models.User;
import com.technologygate.toters.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> users;

    public UsersAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        @SuppressLint("ViewHolder") View v = layoutInflater.inflate(R.layout.row, parent, false);
        try {
            initializeViews(v, position);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return v;
    }

    private void initializeViews(View v, int position) throws ParseException {

        CircleImageView profileIv = v.findViewById(R.id.profileIv);
        TextView nameTv = v.findViewById(R.id.nameTv);
        TextView lastMessageTv = v.findViewById(R.id.lastMessageTv);
        TextView timeTv = v.findViewById(R.id.timeTv);

        if (!users.get(position).getLastMessage().equals("")) {
            lastMessageTv.setVisibility(View.VISIBLE);
            timeTv.setVisibility(View.VISIBLE);
        }

        setData(profileIv, nameTv, lastMessageTv, timeTv, position);
    }

    private void setData(CircleImageView profileIv, TextView nameTv, TextView lastMessageTv, TextView timeTv, int position) throws ParseException {

        String ago = getTimesAgo(position);

        nameTv.setText(users.get(position).getName());
        lastMessageTv.setText(users.get(position).getLastMessage());
        timeTv.setText(ago);
        profileIv.setImageResource(users.get(position).getProfile());

    }

    private String getTimesAgo(int position) throws ParseException {

        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date d1;
        d1 = sdf3.parse(users.get(position).getTimeStamp());
        assert d1 != null;
        return GetTimesAgo.getTimeAgo(d1);
    }
}
