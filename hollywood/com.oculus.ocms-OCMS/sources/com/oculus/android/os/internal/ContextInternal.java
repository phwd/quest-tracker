package com.oculus.android.os.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.UserHandle;
import javax.annotation.Nullable;

public class ContextInternal {
    public static Intent registerReceiverAsUser(Context context, BroadcastReceiver broadcastReceiver, UserHandle userHandle, IntentFilter intentFilter, @Nullable String str, @Nullable Handler handler) {
        return context.registerReceiverAsUser(broadcastReceiver, userHandle, intentFilter, str, handler);
    }

    public static boolean bindServiceAsUser(Context context, Intent intent, ServiceConnection serviceConnection, int i, UserHandle userHandle) {
        return context.bindServiceAsUser(intent, serviceConnection, i, userHandle);
    }
}
