package com.example.tianbi.proj2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by TIANBI on 2/29/16.
 */
public class detailed extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view_nancy_pelosi);
        Intent infoPassed = getIntent();
        Bundle letter_received = infoPassed.getExtras();

        if (letter_received != null) {

            String name = letter_received.getString("rep_name");
            TextView name_in_detail = (TextView) findViewById(R.id.name_in_detail);
            name_in_detail.append(" " + name);

            String party = letter_received.getString("rep_party");
            TextView party_in_detail = (TextView) findViewById(R.id.Party_in_detail);
            party_in_detail.append(" " + party);

//            String email = letter_received.getString("rep_email");
            TextView detail = (TextView) findViewById(R.id.Committee_in_detail);
            detail.append("Committee of" + name);

            TextView bill = (TextView) findViewById(R.id.Bill_in_detail);
            bill.append(" Bill of " + name );

            int pic = Integer.parseInt(letter_received.getString("rep_photo"));
            ImageView rep_pic = (ImageView) findViewById(R.id.detail_pic);
            rep_pic.setImageResource(pic);
        }

    }


}
