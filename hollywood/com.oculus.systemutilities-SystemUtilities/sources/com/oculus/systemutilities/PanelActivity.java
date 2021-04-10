package com.oculus.systemutilities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PanelActivity extends Activity {
    private static final String TAG = "PanelActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.vrshell", SystemUtilitiesApplication.ACTIVITY_NAME_SHELL));
        intent.setData(Uri.parse("apk://" + getPackageName()));
        startActivity(intent);
        finish();
    }
}
