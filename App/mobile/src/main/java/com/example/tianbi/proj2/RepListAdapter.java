package com.example.tianbi.proj2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TIANBI on 3/1/16.
 */
public class RepListAdapter extends BaseAdapter{
    private Context mContext;
    private List<reps> mreps = new ArrayList<>();
    private JSONArray jr;
//    String rePid = "";

    public RepListAdapter(Context mContext, JSONArray jArray) {
        this.mContext = mContext;
        this.jr = jArray;
        for(int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject jb = jArray.getJSONObject(i);
                String id = jb.getString("bioguide_id").toString();
                String end = jb.getString("term_end").toString();
                String firstName = jb.getString("first_name").toString();
                String lastName = jb.getString("last_name").toString();
                String party = jb.getString("party").toString();
                String website = jb.getString("website").toString();
                String email = jb.getString("oc_email").toString();
                String tweet = jb.getString("twitter_id").toString();
                mreps.add(new reps(id, tweet, party, website, email, tweet, end));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

    public List<reps> getList(){
        return mreps;
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

        fullname.append(mreps.get(position).getRep_fullname());
        String party_rep = mreps.get(position).getRep_party();
        if(party_rep.equals("D")){
        party.append("Democratic");}
        else {
            party.append("Republic");
        }
        email.append(mreps.get(position).getRep_email());
        website.append(mreps.get(position).getRep_website());
        lastTweet.append(mreps.get(position).getRep_lastTweet());
//        photo.setImageResource(mreps.get(position).getRep_photo());
        String temp = "";
        temp = mreps.get(position).getId();
        Picasso.with(mContext).load("https://theunitedstates.io/images/congress/225x275/" +
                temp + ".jpg").into(photo);

//        v.setTag(mreps.get(position).getId());
//        i = v.getId();
        return v;
    }
}
