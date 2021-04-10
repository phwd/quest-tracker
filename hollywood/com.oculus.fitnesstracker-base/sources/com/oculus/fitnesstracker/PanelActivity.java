package com.oculus.fitnesstracker;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PanelActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        intent.setData(Uri.parse("apk://com.oculus.fitnesstracker"));
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        finish();
    }
}
