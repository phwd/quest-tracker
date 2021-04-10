package com.oculus.gamingactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.oculus.common.build.BuildConstants;

public class PanelActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.oculus.vrshell", BuildConstants.ACTIVITY_NAME_SHELL));
        intent.setData(Uri.parse("apk://com.oculus.gamingactivity"));
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        finish();
    }
}
