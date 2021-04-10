package com.oculus.fitnesstracker.trex;

import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.Log;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.fitnesstracker.provider.FitnessDataContract;
import com.oculus.os.ActivityManagerUtils;

public class OVRFitnessDataInternal {
    private static final String TAG = "OVRFitnessDataInternal";
    private static UserSwitchReceiver broadcastReceiver = null;
    private static Uri effortUri = FitnessDataContract.uriForEffort();
    private static long retryTimeMs = 0;
    private static final Uri shutdownUri = FitnessDataContract.uriForShutdown();
    private static final int waitSec = 30;

    /* access modifiers changed from: package-private */
    public static class UserSwitchReceiver extends BroadcastReceiver {
        Context mContext = null;

        public UserSwitchReceiver(Context context) {
            this.mContext = context;
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(OVRFitnessDataInternal.TAG, "Logged in User has changed...");
            Uri unused = OVRFitnessDataInternal.effortUri = OVRFitnessDataInternal.getEffortUri();
            this.mContext = context;
        }
    }

    private static void startUserSwitchReceiver(Context context) {
        UserSwitchReceiver userSwitchReceiver = new UserSwitchReceiver(context);
        broadcastReceiver = userSwitchReceiver;
        userSwitchReceiver.mContext.registerReceiver(broadcastReceiver, new IntentFilter("android.intent.action.USER_SWITCHED"));
    }

    public static void shutdownSignal() {
        Log.d(TAG, String.format("Shutdown signal received, will wait for %d seconds", Integer.valueOf((int) waitSec)));
        retryTimeMs = System.currentTimeMillis() + 30000;
    }

    /* access modifiers changed from: private */
    public static Uri getEffortUri() {
        int i;
        try {
            i = ActivityManagerUtils.getUserIdFromHandle(ActivityManagerUtils.getCurrentUser());
        } catch (Exception e) {
            Log.e(TAG, "Error getting Current userId....", e);
            i = -1;
        }
        return FitnessDataContract.uriForEffort(i);
    }

    public static synchronized void requestInsertFitnessData(Context context, double d, long j, int i, String str, int i2, int i3, int i4, int i5) {
        synchronized (OVRFitnessDataInternal.class) {
            if (broadcastReceiver == null) {
                startUserSwitchReceiver(context);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("effort", Double.valueOf(d));
            contentValues.put("setDate", Long.valueOf(j));
            contentValues.put(FitnessTrackerMoveContract.Calories.IS_ACTIVE, Integer.valueOf(i));
            contentValues.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, str);
            contentValues.put("pluginVersionMajor", Integer.valueOf(i2));
            contentValues.put("pluginVersionMinor", Integer.valueOf(i3));
            contentValues.put("pluginVersionPatch", Integer.valueOf(i4));
            contentValues.put("pluginVersionChange", Integer.valueOf(i5));
            if (System.currentTimeMillis() >= retryTimeMs) {
                String str2 = TAG;
                Log.d(str2, "Requesting Insert FitnessData: " + i2 + '.' + i3 + '.' + i4 + '.' + i5);
                context.getContentResolver();
                ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(effortUri.getAuthority());
                try {
                    Uri insert = acquireUnstableContentProviderClient.insert(effortUri, contentValues);
                    if (insert != null) {
                        if (insert.compareTo(shutdownUri) == 0) {
                            shutdownSignal();
                            acquireUnstableContentProviderClient.close();
                        }
                    }
                } catch (Exception e) {
                    String str3 = TAG;
                    Log.d(str3, "Failed due to exception: " + e);
                    shutdownSignal();
                } finally {
                    acquireUnstableContentProviderClient.close();
                }
            }
        }
    }
}
