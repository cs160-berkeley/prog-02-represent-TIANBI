package com.example.tianbi.proj2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TIANBI on 2/29/16.
 */
public class congress extends Activity{
    private ListView lvreps;
    private RepListAdapter adapter;
    private List<reps> mreps;
    private String mLatitudeText;
    private String mLongitudeText;
    private ArrayList<JSONObject> representativesArray;
    JSONArray representativesJSONArray;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congressional_view_cal_12th);
//        representativesArray = new ArrayList<JSONObject>();

        Intent receiveZip = getIntent();
        Bundle zipReceived = receiveZip.getExtras();
        String passedZip = zipReceived.getString("zipCodeToPass");
        mLatitudeText = zipReceived.getString("Latitude");
        mLongitudeText = zipReceived.getString("Longitude");
        String url = "";
        if (passedZip == null){
//            String apikeycc = "419b5accc760498dbf55e71e0051dc31";
            String baseURLcc = "http://congress.api.sunlightfoundation.com/legislators/locate?";
            String CorrdinatesCodeAdditioncc ="latitude="+mLatitudeText+"&longitude="+mLongitudeText+"&apikey=419b5accc760498dbf55e71e0051dc31";
            url = baseURLcc+CorrdinatesCodeAdditioncc;

            Toast.makeText(congress.this, url, Toast.LENGTH_SHORT).show();

        }else{
            String apikey = "419b5accc760498dbf55e71e0051dc31";
            String baseURL = "http://congress.api.sunlightfoundation.com";
            String zipCodeAddition = "/legislators/locate?zip="+passedZip+"&apikey="+apikey;
            url = baseURL+zipCodeAddition;
            Toast.makeText(congress.this, url, Toast.LENGTH_SHORT).show();

        }
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            new WebInfoGrabber().execute(url);
        }

//
//        lvreps = (ListView)findViewById(R.id.id_list_view_product);
//        mreps = new ArrayList<>();
//        mreps.add(new reps(1,rep1FirstName + rep1LastName,rep1Party,rep1Email,rep1Website, rep1Tweet,R.drawable.nancypelosi));
//        mreps.add(new reps(1,"Barbara Boxer: ","Democratic ","Barbara Boxer Email","Website: Barbara Boxer Web", "LastTweet : Barbara Boxer is awesome!",R.drawable.barbaraboxer));
//        mreps.add(new reps(1, "Dianne Feinstein: ", "Democratic ", "Dianne Feinstein Email ", "Website: Dianne Feinstein Web", "LastTweet : Dianne Feinstein is awesome!", R.drawable.diannefeinstein));
//        adapter = new RepListAdapter(getApplicationContext(),mreps);
//        lvreps.setAdapter(adapter);
//        lvreps.setOnItemClickListener(new AdapterView.OnItemClickListe954ner(){
//            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                itemListener(lvreps, position);
//            }
//
//        });



    }

//    congress.api.sunlightfoundation.com/legislators/locate?latitude=65.9667&longitude=65.9667&apikey=419b5accc760498dbf55e71e0051dc31
    private class WebInfoGrabber extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... url) {
//            URL apiurlcc;
                try {
                    URL apiurlcc = new URL(url[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) apiurlcc.openConnection();
                    urlConnection.connect();
                try {
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( urlConnection.getInputStream()));
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    Log.d("representatives", stringBuilder.toString());
                    return stringBuilder.toString();

                }
                finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                    return ("URL is not responding============================");
                } catch (IOException e) {
                        e.printStackTrace();
                    return ("URL is invalid============================");
                }catch (Exception e){
                        Log.e("ERROR", e.getMessage(),e);
                    return null;
                }
        }

        protected void onPostExecute(String response) {
            JSONObject JSONobj;

            if (response != null) {
                try{
                    JSONobj = (JSONObject) new JSONTokener(response).nextValue();
                    representativesJSONArray = JSONobj.getJSONArray("results");
                    System.out.println(representativesJSONArray.toString());
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }

            lvreps = (ListView)findViewById(R.id.id_list_view_product);
            adapter = new RepListAdapter(getApplicationContext(),representativesJSONArray);
            mreps = adapter.getList();
            lvreps.setAdapter(adapter);
            lvreps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    itemListener(lvreps, position);
                }

            });

        }

    }


    public void itemListener(View view, int position) {
        Bundle letter = new Bundle();
        Intent sendInfo = new Intent(getApplicationContext(), detailed.class);
        letter.putString("rep_id",mreps.get(position).getId());
        letter.putString("rep_name", mreps.get(position).getRep_fullname());
        letter.putString("rep_party", mreps.get(position).getRep_party());
        letter.putString("rep_email", mreps.get(position).getRep_email());
        letter.putString("rep_web", mreps.get(position).getRep_website());
        letter.putString("rep_tweet", mreps.get(position).getRep_lastTweet());
        letter.putString("rep_end",mreps.get(position).getRep_end());
//        letter.putString("rep_photo",String.valueOf(mreps.get(position).getRep_photo()));
        sendInfo.putExtras(letter);
        startActivity(sendInfo);


    }


}
