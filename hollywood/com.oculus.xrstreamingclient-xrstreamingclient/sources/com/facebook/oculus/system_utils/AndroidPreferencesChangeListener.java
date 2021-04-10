package com.facebook.oculus.system_utils;

import android.content.SharedPreferences;
import android.util.Log;

class AndroidPreferencesChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = "jni::PrefChange";
    private final boolean enableLogging = false;
    private final SharedPreferences sharedPreferences;

    private native void onChange();

    public AndroidPreferencesChangeListener(SharedPreferences sharedPreferences2) {
        Log.v(TAG, "Created listener");
        this.sharedPreferences = sharedPreferences2;
        sharedPreferences2.registerOnSharedPreferenceChangeListener(this);
    }

    private void unregister() {
        Log.v(TAG, "Unregistering listener");
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
        onChange();
    }
}
