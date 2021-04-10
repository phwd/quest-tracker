package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher"})
public class CheckedIntentLauncher extends BaseIntentLauncher {
    private static final String ACTIVITY = "Activity";
    private static final String CONTEXT = "Context";
    private static final String FRAGMENT = "Fragment";
    private static final String INTENT = "Intent";
    private static final String SERVICE_CONNECTION = "ServiceConnection";
    private final IntentLauncher mDelegate;

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchReactNativeActivity(Intent intent, Context context) {
        return false;
    }

    public CheckedIntentLauncher(IntentLauncher intentLauncher) {
        this.mDelegate = intentLauncher;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Context context) {
        check(INTENT, intent);
        check(CONTEXT, context);
        return this.mDelegate.launchActivity(intent, context);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Activity activity) {
        check(INTENT, intent);
        check(ACTIVITY, activity);
        return this.mDelegate.launchActivityForResult(intent, i, activity);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @TargetApi(16)
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        check(INTENT, intent);
        check(ACTIVITY, activity);
        return this.mDelegate.launchActivityForResult(intent, i, bundle, activity);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Fragment fragment) {
        check(INTENT, intent);
        check(FRAGMENT, fragment);
        return this.mDelegate.launchActivityForResult(intent, i, fragment);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @Nullable
    public ComponentName launchService(Intent intent, Context context) {
        check(INTENT, intent);
        check(CONTEXT, context);
        return this.mDelegate.launchService(intent, context);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean bindToService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        check(INTENT, intent);
        check(SERVICE_CONNECTION, serviceConnection);
        check(CONTEXT, context);
        return this.mDelegate.bindToService(intent, serviceConnection, i, context);
    }

    private static final void check(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(str + " cannot be null");
        }
    }
}
