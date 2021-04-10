package com.oculus.explore;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PanelActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        intent.setData(Uri.parse("apk://com.oculus.explore"));
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        finish();
    }
}
