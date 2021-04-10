package com.oculus.xrinput.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.oculus.os.ActivityManagerUtils;

class UserSwitchReceiver extends BroadcastReceiver {
    private static final String TAG = "UserSwitchReceiver";
    private final Context context;
    private final long nativePointer;

    private static native void notifyUserSwitched(long j);

    public static UserSwitchReceiver getNewInstance(Context context2, long ptr) {
        return new UserSwitchReceiver(context2, ptr);
    }

    public UserSwitchReceiver(Context context2, long ptr) {
        this.context = context2;
        this.nativePointer = ptr;
    }

    public void registerSwitch() {
        Log.d(TAG, "Running Register Switch");
        this.context.registerReceiver(this, new IntentFilter("android.intent.action.USER_SWITCHED"));
    }

    public void unregisterSwitch() {
        Log.d(TAG, "Unregistering  User Switch receiver");
        this.context.unregisterReceiver(this);
    }

    public void onReceive(Context context2, Intent intent) {
        Log.d(TAG, "OnReceive Logged in User has changed...");
        notifyUserSwitched(this.nativePointer);
    }

    public static int getUserId() {
        try {
            return ActivityManagerUtils.getUserIdFromHandle(ActivityManagerUtils.getCurrentUser());
        } catch (SecurityException ex) {
            Log.e(TAG, "Can't get Current User Id", ex);
            return 0;
        } catch (NoSuchMethodError e) {
            Log.e(TAG, "Can't find getUserId");
            return 0;
        }
    }
}
