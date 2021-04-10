package com.oculus.xrstreamingclient;

import android.app.NativeActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.oculus.os.SettingsManager;

public class MainActivity extends NativeActivity {
    private static final String ANDROID_PERMISSION_READ_PRIVILEGED_PHONE_STATE = "android.permission.READ_PRIVILEGED_PHONE_STATE";
    private static final int REQUEST_MULTIPLE_PERMISSIONS = 200;
    private static final String TAG = "xrstreamingclient";
    private float _headphonesVolume = 0.0f;
    private float _microphoneVolume = 1.0f;
    private String[] debugPermissions = {"android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.INTERNET"};
    private boolean hasMicrophonePermissions_ = false;
    private RemoteFrameDumpReceiver mRemoteFrameDumpReceiver;
    private SettingsManager mSettingsManager;
    private WifiManager.WifiLock mWifiLockLowLatency;
    private String[] releasePermissions = {"android.permission.RECORD_AUDIO"};

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void remoteFrameDumpStart();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void remoteFrameDumpToggle();

    static {
        System.loadLibrary("vrapi");
        System.loadLibrary(TAG);
        Log.i(TAG, "----------------------------------------------------------------");
        Log.i(TAG, BuildConfig.APPLICATION_ID);
        Log.i(TAG, "  Build Type: profile (debug=false)");
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == REQUEST_MULTIPLE_PERMISSIONS) {
            for (int i2 : iArr) {
                if (i2 != 0) {
                    finish();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRemoteFrameDumpReceiver = new RemoteFrameDumpReceiver() {
            /* class com.oculus.xrstreamingclient.MainActivity.AnonymousClass1 */

            @Override // com.oculus.xrstreamingclient.RemoteFrameDumpReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null) {
                    String action = intent.getAction();
                    char c = 65535;
                    int hashCode = action.hashCode();
                    if (hashCode != -768221616) {
                        if (hashCode == 1221699110 && action.equals(RemoteFrameDumpReceiver.DUMP_ACTION)) {
                            c = 1;
                        }
                    } else if (action.equals(RemoteFrameDumpReceiver.START_ACTION)) {
                        c = 0;
                    }
                    if (c == 0) {
                        MainActivity.this.remoteFrameDumpStart();
                    } else if (c == 1) {
                        MainActivity.this.remoteFrameDumpToggle();
                    }
                }
            }
        };
        this.mSettingsManager = new SettingsManager();
        requestPermissions(this.releasePermissions, REQUEST_MULTIPLE_PERMISSIONS);
        this.hasMicrophonePermissions_ = checkCallingOrSelfPermission("com.oculus.permission.READ_SETTINGS") == 0 && checkCallingOrSelfPermission("com.oculus.permission.WRITE_SETTINGS") == 0;
    }

    public void setWifiLock() {
        if (Build.VERSION.SDK_INT >= 29) {
            WifiManager.WifiLock createWifiLock = ((WifiManager) getSystemService("wifi")).createWifiLock(4, "FullLowLatency");
            this.mWifiLockLowLatency = createWifiLock;
            createWifiLock.acquire();
            if (this.mWifiLockLowLatency.isHeld()) {
                Log.d(TAG, "Wakelock created");
            } else {
                Log.e(TAG, "Failed to create Wakelock!");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RemoteFrameDumpReceiver.START_ACTION);
        intentFilter.addAction(RemoteFrameDumpReceiver.DUMP_ACTION);
        registerReceiver(this.mRemoteFrameDumpReceiver, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        unregisterReceiver(this.mRemoteFrameDumpReceiver);
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mWifiLockLowLatency.isHeld()) {
            this.mWifiLockLowLatency.release();
        }
    }

    private int getApkVersionCode() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Failed to get package name", e);
        }
    }

    private String getHmdSerial() {
        if (Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        if (Build.VERSION.SDK_INT < 29) {
            if (checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                throw new RuntimeException("getHmdSerial() does not request android.permission.READ_PHONE_STATE for Android API v" + Build.VERSION.SDK_INT + " because " + getPackageName() + " does not support this platform");
            }
        } else if (checkSelfPermission(ANDROID_PERMISSION_READ_PRIVILEGED_PHONE_STATE) != 0) {
            throw new SecurityException("getHmdSerial() requires android.permission.READ_PRIVILEGED_PHONE_STATE for Android API v" + Build.VERSION.SDK_INT);
        }
        return Build.getSerial();
    }

    private void showPCDisconnectedDialog(String str) {
        try {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.oculus.vrshell");
            launchIntentForPackage.putExtra("intent_data", Uri.parse("systemux://dialog/oculus_link_disconnected?disconnect_reason=" + str));
            launchIntentForPackage.setFlags(268435456);
            startActivity(launchIntentForPackage);
        } catch (Exception e) {
            Log.e(TAG, "Unable to launch vrshell to show the pc disconneted dialog", e);
        }
    }

    private void resetGuardian() {
        try {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.oculus.vrshell");
            launchIntentForPackage.putExtra("intent_data", Uri.parse("systemux://guardian/roomscale-setup"));
            launchIntentForPackage.putExtra("intent_pkg", getPackageName());
            launchIntentForPackage.setFlags(268435456);
            startActivity(launchIntentForPackage);
        } catch (Exception e) {
            Log.e(TAG, "Unable to launch vrshell to go to guardian setup", e);
        }
    }

    private boolean getPassthroughOnDemandEnabled() {
        return this.mSettingsManager.getBoolean("passthrough_on_demand_enabled", false);
    }

    private void setPassthroughOnDemandEnabled(boolean z) {
        this.mSettingsManager.setBoolean("passthrough_on_demand_enabled", z);
    }

    private boolean getGuardianVisibility() {
        return !this.mSettingsManager.getBoolean("guardian_paused", false);
    }

    private void setGuardianVisibility(boolean z) {
        this.mSettingsManager.setBoolean("guardian_paused", !z);
    }

    private static float Clamp01(float f) {
        return Math.min(Math.max(0.0f, f), 1.0f);
    }

    private int getHmdBatteryPercent() {
        Intent registerReceiver = getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("status", -1);
        int intExtra2 = registerReceiver.getIntExtra("level", -1);
        int intExtra3 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra2 < 0 || intExtra3 < 0) {
            return -1;
        }
        if (intExtra == 5) {
            return 100;
        }
        if (intExtra2 == 0 || intExtra3 == 0) {
            return 0;
        }
        return Math.round(Clamp01(((float) intExtra2) / ((float) intExtra3)) * 100.0f);
    }

    private void setHeadphones(float f, boolean z) {
        int i;
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService("audio");
        int round = Math.round(Clamp01(f) * ((float) audioManager.getStreamMaxVolume(3)));
        if (!z) {
            audioManager.setStreamVolume(3, round, 4);
            i = 100;
        } else {
            i = -100;
        }
        audioManager.adjustVolume(i, 4);
        this._headphonesVolume = f;
    }

    private void setMicrophone(float f, boolean z) {
        this._microphoneVolume = f;
        this.mSettingsManager.setBoolean("mic_muted", z);
    }

    private float getHeadphonesVolume() {
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService("audio");
        return audioManager.isStreamMute(3) ? this._headphonesVolume : Clamp01(((float) audioManager.getStreamVolume(3)) / ((float) Math.max(1, audioManager.getStreamMaxVolume(3))));
    }

    private boolean getHeadphonesIsMute() {
        return ((AudioManager) getApplicationContext().getSystemService("audio")).isStreamMute(3);
    }

    private float getMicrophoneVolume() {
        return this._microphoneVolume;
    }

    private boolean getMicrophoneIsMute() {
        return this.mSettingsManager.getBoolean("mic_muted", true);
    }
}
