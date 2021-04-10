package com.facebook.secure.context;

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

public abstract class BaseIntentLauncher implements IntentLauncher {
    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchReactNativeActivity(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Bundle bundle, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivities(TaskStackBuilder taskStackBuilder, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Activity activity) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Fragment fragment) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Fragment fragment) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivityIfNeeded(Intent intent, int i, Activity activity) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean launchActivityIfNeeded(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    @Nullable
    public ComponentName launchService(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    @Nullable
    public ComponentName launchForegroundService(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean bindToService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean stopService(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean sendBroadcast(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean sendBroadcast(Intent intent, Context context, String str) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public boolean sendOrderedBroadcast(Intent intent, Context context, @Nullable String str, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String str2, @Nullable Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.context.IntentLauncher
    public void registerReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter, @Nullable String str, @Nullable Handler handler) {
        throw new UnsupportedOperationException();
    }
}
