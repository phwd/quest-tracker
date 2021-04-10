package com.oculus.android.os.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import javax.annotation.Nullable;

public class ContextInternal {
    public static void A00(Context context, BroadcastReceiver broadcastReceiver, UserHandle userHandle, IntentFilter intentFilter, @Nullable String str) {
        context.registerReceiverAsUser(broadcastReceiver, userHandle, intentFilter, str, null);
    }
}
