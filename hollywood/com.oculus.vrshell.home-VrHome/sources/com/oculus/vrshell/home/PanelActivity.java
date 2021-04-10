package com.oculus.vrshell.home;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class PanelActivity extends Activity {
    private static final String TAG = "home";

    public void onCreate(Bundle savedInstanceState) {
        String service;
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            service = extras.getString("service");
        } else {
            Log.w(TAG, "PanelActivity - service name is not provided, defaulting to Explore.");
            service = "com.oculus.vrshell.home/.ExploreService";
        }
        intent.setData(Uri.parse(service));
        startActivity(intent);
    }

    public void onStart() {
        Log.i(TAG, "PanelActivity::onStart");
        super.onStart();
        finish();
        Log.i(TAG, "PanelActivity::onStart complete");
    }
}
