package com.oculus.panelapp.anytimeui.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;

public class LaunchDownloadBroadcastReceiver extends BroadcastReceiver {
    private static final String[] ACTIONS_REGISTERED = {SEE_MORE, LAUNCH_APPLICATION};
    private static final String EXTRA_INTENT_DATA = "intent_data";
    private static final String EXTRA_URI = "uri";
    private static final String LAUNCH_APPLICATION = "com.oculus.systemux.download.LAUNCH_APPLICATION";
    private static final String SEE_MORE = "com.oculus.systemux.download.SEE_MORE";
    private static final String TAG = LoggingUtil.tag(LaunchDownloadBroadcastReceiver.class);
    private IntentFilter mIntentFilter = new IntentFilter();
    private AnytimeUIAndroidPanelAppV2 mPanelApp;

    public LaunchDownloadBroadcastReceiver(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        for (String str : ACTIONS_REGISTERED) {
            this.mIntentFilter.addAction(str);
        }
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "action: " + intent.getAction());
        if (intent.getAction() == null) {
            Log.w(TAG, "Received an intent with no action.");
            return;
        }
        String stringExtra = intent.getStringExtra(EXTRA_INTENT_DATA);
        if (stringExtra == null) {
            Log.w(TAG, "Received an action with no intent data.");
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -902069882) {
            if (hashCode == 1582042435 && action.equals(SEE_MORE)) {
                c = 0;
            }
        } else if (action.equals(LAUNCH_APPLICATION)) {
            c = 1;
        }
        if (c == 0 || c == 1) {
            String stringExtra2 = intent.getStringExtra("uri");
            AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = this.mPanelApp;
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            anytimeUIAndroidPanelAppV2.actionNavigate(stringExtra, stringExtra2);
            return;
        }
        Log.w(TAG, "Received unknown action: " + intent.getAction());
    }

    public IntentFilter getIntentFilter() {
        return this.mIntentFilter;
    }
}
