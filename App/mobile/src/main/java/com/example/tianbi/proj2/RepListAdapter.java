package com.example.tianbi.proj2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TIANBI on 3/1/16.
 */
public class RepListAdapter extends BaseAdapter{
    private Context mContext;
    private List<reps> mreps;
    public int i = 0;

    public RepListAdapter(Context mContext, List<reps> mreps) {
        this.mContext = mContext;
        this.mreps = mreps;
    }


    @Override
    public int getCount() {
        return mreps.size();
    }

    @Override
    public Object getItem(int position) {
        return mreps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.rep_info,null);
        TextView fullname =(TextView)v.findViewById(R.id.id_rep_fullname);
        TextView party =(TextView)v.findViewById(R.id.id_rep_party);
        TextView email =(TextView)v.findViewById(R.id.id_rep_email);
        TextView website =(TextView)v.findViewById(R.id.id_rep_website);
        TextView lastTweet =(TextView)v.findViewById(R.id.id_rep_lastTweet);
        ImageView photo = (ImageView)v.findViewById(R.id.id_rep_photo);

        fullname.setText(mreps.get(position).getRep_fullname());
        party.setText(mreps.get(position).getRep_party());
        email.setText(mreps.get(position).getRep_email());
        website.setText(mreps.get(position).getRep_website());
        lastTweet.setText(mreps.get(position).getRep_lastTweet());
        photo.setImageResource(mreps.get(position).getRep_photo());

        v.setTag(mreps.get(position).getId());
        i = v.getId();
        return v;
    }
}
