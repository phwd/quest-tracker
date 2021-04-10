package com.oculus.os.vrbrowserlauncher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getString(R.string.vr_shell_pkg_name));
        launchIntentForPackage.setAction("android.intent.action.VIEW");
        launchIntentForPackage.setData(Uri.parse("apk://" + getString(R.string.vr_browser_panel_app)));
        Uri data = getIntent().getData();
        if (data != null) {
            launchIntentForPackage.putExtra("uri", data);
        }
        finish();
        getApplicationContext().startActivity(launchIntentForPackage);
    }
}
