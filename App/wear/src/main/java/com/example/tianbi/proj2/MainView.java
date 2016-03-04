package com.example.tianbi.proj2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.widget.Toast;

/**
 * Created by TIANBI on 3/2/16.
 */
public class MainView extends WearableActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor msenser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String ZipCodeReceived = extras.getString("ZipCodeJustReceived");
        Toast.makeText(getApplicationContext(), "The zipcode received is" + ZipCodeReceived, Toast.LENGTH_LONG).show();

        setContentView(R.layout.reps);
        final Resources res = getResources();
        final GridViewPager pager = (GridViewPager) findViewById(R.id.id_pager);
        pager.setAdapter(new GridViewPagerAdapter(this, getFragmentManager()));

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        msenser = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

        public void rep_on_click(View view) {
        Intent sendIntent = new Intent(this, WatchToPhoneService.class);
        Bundle test = new Bundle();
        test.putString("test", "...");
        sendIntent.putExtras(test);
        startService(sendIntent);
        }


        public void showVoteRecord (View view){
            Intent showVote = new Intent(this, vote2012.class);
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String ZipCodeReceived = extras.getString("ZipCodeJustReceived");
            showVote.putExtra("Zip",ZipCodeReceived );
            startActivity(showVote);
//            Toast.makeText(getApplicationContext(), "The zipcode received is" + ZipCodeReceived, Toast.LENGTH_LONG).show();



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float acceleration = (float)Math.sqrt(x+y+z);
        if (acceleration > 300) {
            Intent sensor_m = new Intent(this,WatchToPhoneService.class);
            Bundle sensor_bundle = new Bundle();
            sensor_bundle.putString("acc", String.valueOf(Math.random()*100));
            sensor_m.putExtras(sensor_bundle);
            startService(sensor_m);
//        sensor_bundle.putString("sensor_display", toString(Math.random()+77777));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, msenser, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
