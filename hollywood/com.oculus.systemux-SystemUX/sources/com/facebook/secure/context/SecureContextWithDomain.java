package com.facebook.secure.context;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import javax.annotation.Nullable;

@Deprecated
public class SecureContextWithDomain {
    public static boolean launchInternalActivity(Intent intent, Context context, String str) {
        return internalWithDomain(str).launchActivity(intent, context);
    }

    public static boolean launchInternalActivity(Intent intent, Bundle bundle, Context context, String str) {
        return internalWithDomain(str).launchActivity(intent, bundle, context);
    }

    public static boolean launchInternalActivities(Intent[] intentArr, Context context, String str) {
        return internalWithDomain(str).launchActivities(intentArr, context);
    }

    public static boolean launchInternalActivities(TaskStackBuilder taskStackBuilder, Context context, String str) {
        return internalWithDomain(str).launchActivities(taskStackBuilder, context);
    }

    public static boolean launchInternalActivityForResult(Intent intent, int i, Activity activity, String str) {
        return internalWithDomain(str).launchActivityForResult(intent, i, activity);
    }

    public static boolean launchInternalActivityForResult(Intent intent, int i, Fragment fragment, String str) {
        return internalWithDomain(str).launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchInternalService(Intent intent, Context context, String str) {
        return internalWithDomain(str).launchService(intent, context);
    }

    public static boolean bindInternalService(Intent intent, ServiceConnection serviceConnection, int i, Context context, String str) {
        return internalWithDomain(str).bindToService(intent, serviceConnection, i, context);
    }

    public static boolean stopInternalService(Intent intent, Context context, String str) {
        return internalWithDomain(str).stopService(intent, context);
    }

    public static boolean sendInternalBroadcast(Intent intent, Context context, String str) {
        return internalWithDomain(str).sendBroadcast(intent, context);
    }

    public static boolean sendInternalBroadcast(Intent intent, Context context, String str, String str2) {
        return internalWithDomain(str).sendBroadcast(intent, context, str2);
    }

    public static boolean launchSameKeyActivity(Intent intent, Context context, String str) {
        return sameKeyWithDomain(str).launchActivity(intent, context);
    }

    public static boolean launchSameKeyActivityForResult(Intent intent, int i, Activity activity, String str) {
        return sameKeyWithDomain(str).launchActivityForResult(intent, i, activity);
    }

    public static boolean launchSameKeyActivityForResult(Intent intent, int i, Fragment fragment, String str) {
        return sameKeyWithDomain(str).launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchSameKeyService(Intent intent, Context context, String str) {
        return sameKeyWithDomain(str).launchService(intent, context);
    }

    public static boolean bindSameKeyService(Intent intent, ServiceConnection serviceConnection, int i, Context context, String str) {
        return sameKeyWithDomain(str).bindToService(intent, serviceConnection, i, context);
    }

    public static boolean sendSameKeyBroadcast(Intent intent, Context context, String str) {
        return sameKeyWithDomain(str).sendBroadcast(intent, context);
    }

    public static boolean sendSameKeyBroadcast(Intent intent, Context context, String str, String str2) {
        return sameKeyWithDomain(str).sendBroadcast(intent, context, str2);
    }

    public static boolean launchFamilyActivity(Intent intent, Context context, String str) {
        return familyWithDomain(str).launchActivity(intent, context);
    }

    public static boolean launchFamilyActivityForResult(Intent intent, int i, Activity activity, String str) {
        return familyWithDomain(str).launchActivityForResult(intent, i, activity);
    }

    public static boolean launchFamilyActivityForResult(Intent intent, int i, Fragment fragment, String str) {
        return familyWithDomain(str).launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchFamilyService(Intent intent, Context context, String str) {
        return familyWithDomain(str).launchService(intent, context);
    }

    public static boolean bindFamilyService(Intent intent, ServiceConnection serviceConnection, int i, Context context, String str) {
        return familyWithDomain(str).bindToService(intent, serviceConnection, i, context);
    }

    public static boolean sendFamilyBroadcast(Intent intent, Context context, String str) {
        return familyWithDomain(str).sendBroadcast(intent, context);
    }

    public static boolean sendFamilyBroadcast(Intent intent, Context context, String str, String str2) {
        return familyWithDomain(str).sendBroadcast(intent, context, str2);
    }

    private static ScopedIntentLauncher internalWithDomain(String str) {
        return SecureContextHelper.get().internalWithDomain(str);
    }

    private static ScopedIntentLauncher sameKeyWithDomain(String str) {
        return SecureContextHelper.get().sameKeyWithDomain(str);
    }

    private static ScopedIntentLauncher familyWithDomain(String str) {
        return SecureContextHelper.get().familyWithDomain(str);
    }
}
