package com.example.tianbi.proj2;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by TIANBI on 3/2/16.
 */
public class WatchListenerService extends WearableListenerService {
    private static final String zip1 = "/94722";
    private static final String zip2 = "/94721";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase( zip1 ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainView.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra("ZipCodeJustReceived", "94722");
            Log.d("T", "about to start watch MainActivity with zip: zip1");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( zip2 )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainView.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            intent.putExtra("ZipCodeJustReceived", "94721");
            Log.d("T", "about to start watch MainActivity with zip: zip2");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }


}
