package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import com.facebook.secure.receiver.SecureBroadcastReceiver;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher"})
public interface IntentLauncher {
    boolean bindToService(Intent intent, ServiceConnection serviceConnection, int i, Context context);

    boolean launchActivities(TaskStackBuilder taskStackBuilder, Context context);

    boolean launchActivity(Intent intent, Context context);

    boolean launchActivity(Intent intent, Bundle bundle, Context context);

    boolean launchActivityForResult(Intent intent, int i, Activity activity);

    boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Activity activity);

    boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Fragment fragment);

    boolean launchActivityForResult(Intent intent, int i, Fragment fragment);

    boolean launchActivityIfNeeded(Intent intent, int i, Activity activity);

    boolean launchActivityIfNeeded(Intent intent, int i, @Nullable Bundle bundle, Activity activity);

    @Nullable
    ComponentName launchForegroundService(Intent intent, Context context);

    boolean launchReactNativeActivity(Intent intent, Context context);

    @Nullable
    ComponentName launchService(Intent intent, Context context);

    void registerReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter, @Nullable String str, @Nullable Handler handler);

    boolean sendBroadcast(Intent intent, Context context);

    boolean sendBroadcast(Intent intent, Context context, String str);

    boolean sendOrderedBroadcast(Intent intent, Context context, @Nullable String str, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String str2, @Nullable Bundle bundle);

    boolean stopService(Intent intent, Context context);
}
