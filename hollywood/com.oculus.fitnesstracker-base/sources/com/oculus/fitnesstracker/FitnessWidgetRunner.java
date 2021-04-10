package com.oculus.fitnesstracker;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.panellib.SystemProperties;

public class FitnessWidgetRunner {
    private static final String OVERLAY_ACTION = "com.oculus.vrshell.intent.action.WIDGET";
    private static final String OVERLAY_COMPONENT_CLASS = "com.oculus.vrshell.ShellOverlayBroadcastReceiver";
    private static final String OVERLAY_COMPONENT_PACKAGE = "com.oculus.vrshell";
    private static final String OVERLAY_DATA_URI_AUTHORITY = "widget";
    private static final String OVERLAY_DATA_URI_SCHEME = "systemux";
    private static final String OVERLAY_EXTRA_URI = "com.oculus.fitnesstracker/com.oculus.fitnesstracker.FitnessWidgetService";
    private static final String OVERLAY_EXTRA_URI_REACT = "com.oculus.fitnesstracker/com.oculus.fitnesstracker.FitnessReactWidgetService";
    private static final String SETTING_FITNESS_TRACKING_ENABLED = "fitness_tracking_enabled";
    private static final String SETTING_FITNESS_TRACKING_OVERLAY_ENABLED = "fitness_tracking_overlay_enabled";
    private static final String TAG = "FitnessWidgetRunner";
    private static FitnessWidgetRunner sInstance;
    private final Context mContext;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private final SettingsObserverCallback mSettingsObserverCallback = new SettingsObserverCallback() {
        /* class com.oculus.fitnesstracker.FitnessWidgetRunner.AnonymousClass1 */

        @DoNotStrip
        public final void onSettingChange(String str) {
            String str2 = FitnessWidgetRunner.TAG;
            Log.d(str2, "Setting changed:" + str);
            FitnessWidgetRunner.this.enableOrDisableWidget();
        }
    };

    public static FitnessWidgetRunner getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new FitnessWidgetRunner(context);
        }
        return sInstance;
    }

    private FitnessWidgetRunner(Context context) {
        Log.d(TAG, "constructor");
        this.mContext = context;
        Handler handler = new Handler(this.mContext.getMainLooper());
        this.mSettingsManager.registerSettingsObserver(SETTING_FITNESS_TRACKING_ENABLED, this.mSettingsObserverCallback, handler);
        this.mSettingsManager.registerSettingsObserver(SETTING_FITNESS_TRACKING_OVERLAY_ENABLED, this.mSettingsObserverCallback, handler);
        enableOrDisableWidget();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void enableOrDisableWidget() {
        boolean z = false;
        if (this.mSettingsManager.getBoolean(SETTING_FITNESS_TRACKING_ENABLED, false) && this.mSettingsManager.getBoolean(SETTING_FITNESS_TRACKING_OVERLAY_ENABLED, false)) {
            z = true;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "Enabling" : "Disabling");
        sb.append(" overlay");
        Log.d(str, sb.toString());
        this.mContext.sendBroadcast(new Intent(OVERLAY_ACTION, new Uri.Builder().scheme(OVERLAY_DATA_URI_SCHEME).authority(OVERLAY_DATA_URI_AUTHORITY).build()).setComponent(new ComponentName(OVERLAY_COMPONENT_PACKAGE, OVERLAY_COMPONENT_CLASS)).putExtra("uri", "1".equals(SystemProperties.getString("persist.fitness.react.overlay.enable", "0")) ? OVERLAY_EXTRA_URI_REACT : OVERLAY_EXTRA_URI).putExtra("launch_at_start", z).putExtra("close", !z));
    }
}
