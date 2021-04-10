package com.oculus.socialplatform;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.oculus.common.build.BuildConstants;

public class PanelActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.oculus.vrshell", BuildConstants.ACTIVITY_NAME_SHELL));
        intent.setData(Uri.parse("apk://com.oculus.socialplatform"));
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        finish();
    }
}
