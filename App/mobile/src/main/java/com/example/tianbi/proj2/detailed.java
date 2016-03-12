package com.example.tianbi.proj2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

/**
 * Created by TIANBI on 2/29/16.
 */
public class detailed extends Activity{
    String b1 = "Bills: ";
    String com1 = "Commitees: ";
    JSONArray representativesJSONArray;
    JSONArray representativesJSONArrayCom;
    int leng = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view_nancy_pelosi);
        Intent infoPassed = getIntent();
        Bundle letter_received = infoPassed.getExtras();
        String temp;
        temp = letter_received.getString("rep_id");
        String apikey =  "&apikey=419b5accc760498dbf55e71e0051dc31";
        String baseURL = "http://congress.api.sunlightfoundation.com/committees?member_ids=";
        String Committeeurl = baseURL+temp+apikey;
        String apikey2 = "http://congress.api.sunlightfoundation.com/bills?sponsor_id=";
        String Billurl =apikey2+temp+apikey;

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            System.out.println(Billurl);
            new BillInfoGrabber().execute(Billurl);
            new CommitteeInfoGrabber().execute(Committeeurl);
        }

        if (letter_received != null) {

            String name = letter_received.getString("rep_name");
            TextView name_in_detail = (TextView) findViewById(R.id.name_in_detail);
            name_in_detail.append(" " + name);

            String party = letter_received.getString("rep_party");
            TextView party_in_detail = (TextView) findViewById(R.id.Party_in_detail);
            if (party.equals("D")){
            party_in_detail.append(" " + "Democratic");
            }else {
                party_in_detail.append(" " + "Republic");
            }


//            String email = letter_received.getString("rep_email");
//            TextView detail = (TextView) findViewById(R.id.Committee_in_detail);
//            detail.append("Committee of" + name);

//            TextView bill = (TextView) findViewById(R.id.Bill_in_detail);
//            bill.append(" Bill of " + name );
            TextView EOD = (TextView) findViewById(R.id.Term_in_detail);
            String endDate = letter_received.getString("rep_end");
            EOD.append(endDate);
            ImageView rep_pic = (ImageView) findViewById(R.id.detail_pic);
            Picasso.with(getApplicationContext()).load("https://theunitedstates.io/images/congress/225x275/" +
                    temp + ".jpg").into(rep_pic);


        }

    }

    private class BillInfoGrabber extends AsyncTask<String,Void,String> {

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
            if (representativesJSONArray != null) {
                for (int i = 0; i < representativesJSONArray.length(); i++) {
                    try {
                        JSONObject temp = (JSONObject) representativesJSONArray.get(i);
                        System.out.println(temp);
                        if (i < 3) {
                            b1 += temp.getString("introduced_on") + ", ";
                            if (temp.getString("short_title") != null) {
                                b1 += temp.getString("short_title");
                            } else {
                                b1 += temp.getString("official_title");
                            }
                        } else if (i == 3) {
                            b1 += temp.getString("introduced_on");
                            if (temp.getString("short_title") != null) {
                                b1 += temp.getString("short_title");
                            } else {
                                b1 += temp.getString("official_title");
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            System.out.println(b1);
            TextView bill = (TextView) findViewById(R.id.Bill_in_detail);
            bill.append(b1);


        }

    }

    private class CommitteeInfoGrabber extends AsyncTask<String,Void,String> {

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
            if (representativesJSONArray != null) {
                for (int i=0; i< representativesJSONArray.length() ;i++){
                    try {
                        JSONObject temp = (JSONObject) representativesJSONArray.get(i) ;
                        if (i < 3) {
                            leng = representativesJSONArray.length();
                            com1 += temp.getString("name") + ", ";
                        } else if (i == 3) {
                            com1 += temp.getString("name");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(com1);
            TextView detail = (TextView) findViewById(R.id.Committee_in_detail);
            detail.append(com1);


        }

    }


}
