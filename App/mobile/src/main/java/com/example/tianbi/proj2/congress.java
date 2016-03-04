package com.example.tianbi.proj2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TIANBI on 2/29/16.
 */
public class congress extends Activity{
    private ListView lvreps;
    private RepListAdapter adapter;
    private List<reps> mreps;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congressional_view_cal_12th);
        lvreps = (ListView)findViewById(R.id.id_list_view_product);
        mreps = new ArrayList<>();
        mreps.add(new reps(1,"Nancy Pelosi ","Democratic ","Nancy Pelosi Email ","Website: Nancy Pelosi Web", "LastTweet : Nancy Pelosi is awesome!",R.drawable.nancypelosi));
        mreps.add(new reps(1,"Barbara Boxer: ","Democratic ","Barbara Boxer Email","Website: Barbara Boxer Web", "LastTweet : Barbara Boxer is awesome!",R.drawable.barbaraboxer));
        mreps.add(new reps(1, "Dianne Feinstein: ", "Democratic ", "Dianne Feinstein Email ", "Website: Dianne Feinstein Web", "LastTweet : Dianne Feinstein is awesome!", R.drawable.diannefeinstein));
        adapter = new RepListAdapter(getApplicationContext(),mreps);
        lvreps.setAdapter(adapter);
        lvreps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemListener(lvreps, position);


            }

        });


        Intent receiveZip = getIntent();
        Bundle zipReceived = receiveZip.getExtras();
        String passedZip = zipReceived.getString("zipCodeToPass");

        Toast.makeText(getApplicationContext(), "The zipcode received is" + passedZip, Toast.LENGTH_SHORT).show();


    }
    public void itemListener(View view, int position) {
        Bundle letter = new Bundle();
        Intent sendInfo = new Intent(getApplicationContext(), detailed.class);
        letter.putString("rep_name", mreps.get(position).getRep_fullname());
        letter.putString("rep_party", mreps.get(position).getRep_party());
        letter.putString("rep_email", mreps.get(position).getRep_email());
        letter.putString("rep_web", mreps.get(position).getRep_website());
        letter.putString("rep_tweet", mreps.get(position).getRep_lastTweet());
        letter.putString("rep_photo",String.valueOf(mreps.get(position).getRep_photo()));
        sendInfo.putExtras(letter);
        startActivity(sendInfo);


    }


}
