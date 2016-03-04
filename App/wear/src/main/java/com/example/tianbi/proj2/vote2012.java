package com.example.tianbi.proj2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by TIANBI on 3/1/16.
 */
public class vote2012 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote2012);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String ZipCodeReceived = extras.getString("Zip");
        TextView county = (TextView) findViewById(R.id.id_county);
        county.append(ZipCodeReceived);
//        Toast.makeText(getApplicationContext(), "The zipcode received is" + ZipCodeReceived, Toast.LENGTH_LONG).show();

    }
}
