package com.example.tianbi.proj2;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by TIANBI on 3/3/16.
 */

public class PhoneListenerService extends WearableListenerService {
    private static final String zip1 = "/94722";
    private static final String ACC = "/acc";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());


        if( messageEvent.getPath().equalsIgnoreCase( zip1 ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, detailedviewwatch.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra("ZipCodeJustReceived", "94722");
            Log.d("T", "about to start watch MainActivity with zip: zip1");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( ACC )) {
            String accelerate = new String(messageEvent.getData());
            Toast.makeText(getApplicationContext(), accelerate, Toast.LENGTH_SHORT).show();
        } else {
            super.onMessageReceived( messageEvent );
        }

    }


}

