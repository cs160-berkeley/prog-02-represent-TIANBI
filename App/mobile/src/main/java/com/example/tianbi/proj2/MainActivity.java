package com.example.tianbi.proj2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "cFTRXrpVla5GDPjky7CquBG8f";
    private static final String TWITTER_SECRET = "uQfRnD8LKqw4Y4qMJ4svvnajun1wZ2YsunzWwgaAPFWiBpqnvn";
    private GoogleApiClient mApiClient;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Represent!");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        Spinner spinner = (Spinner) findViewById(R.id.EnterLocationSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.geoSpinnerText, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        buildGoogleApiClient();

        Log.d("Building", "Building Api");
        mApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
//        getAddress();
        mApiClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void LocationButtonListener(View view) {
        Bundle letter = new Bundle();
        EditText zip = (EditText) findViewById(R.id.EnterZipEditText);
        String zipentered = zip.getText().toString();

//        Intent sendIntent = new Intent(this, PhoneToWatchService.class);
//        sendIntent.putExtra("ZipCodeReceived", zipentered);
//        startService(sendIntent);

        Intent locationSearch = new Intent(this, congress.class);
        letter.putString("zipCodeToPass", zipentered);
        locationSearch.putExtras(letter);
        startActivity(locationSearch);
    }

    public void CurrentLocationButtonListener(View view) {
//        Intent sendIntent = new Intent(this, PhoneToWatchService.class);
//        sendIntent.putExtra("Latitude", mLatitudeText);
//        sendIntent.putExtra("Longitude", mLongitudeText);
//        startService(sendIntent);
        Bundle letter = new Bundle();
        Intent locationSearch = new Intent(this, congress.class);
        letter.putString("Latitude", mLatitudeText);
        letter.putString("Longitude", mLongitudeText);
        locationSearch.putExtras(letter);
        startActivity(locationSearch);
    }

    // Create an instance of GoogleAPIClient.
//    protected void buildGoogleApiClient() {
//        Log.d("Building", "Building Api");
//        mApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.d("CONNECTED", "I am connected");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
        if(mLastLocation != null){
            mLatitudeText = String.valueOf(mLastLocation.getLatitude());
            mLongitudeText = String.valueOf(mLastLocation.getLongitude());
            Log.d("latitude",mLatitudeText);
            Log.d("longitude", mLongitudeText);
            Toast.makeText(MainActivity.this,mLatitudeText +  mLongitudeText ,Toast.LENGTH_LONG).show();
        }else {
            Log.d("String", "mLastLocation is null");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mApiClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("FAILED", connectionResult.toString());

    }
    //reverse geocoding
//    public void getAddress(){
//        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//        List<Address> addressList;
//        try{
//            addressList = geocoder.getFromLocation(Double.parseDouble(mLatitudeText),Double.parseDouble(mLongitudeText),2);
//            if(addressList != null && addressList.size() >0){
//                Address adr = addressList.get(0);
//                String sublocality = adr.getSubLocality();
//                String locality = adr.getLocality();
//                String country = adr.getCountryName();
//                String county = adr.getSubAdminArea();
//                Toast.makeText(MainActivity.this,"Sub local:" +sublocality + "local:"+ locality+ country+ county,Toast.LENGTH_LONG).show();
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
