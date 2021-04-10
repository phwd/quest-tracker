package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import javax.annotation.Nullable;

public class SecureContext {
    public static boolean launchInternalActivity(Intent intent, Context context) {
        return internal().launchActivity(intent, context);
    }

    public static boolean launchInternalActivity(Intent intent, Bundle bundle, Context context) {
        return internal().launchActivity(intent, bundle, context);
    }

    public static boolean launchInternalActivities(Intent[] intentArr, Context context) {
        return internal().launchActivities(intentArr, context);
    }

    public static boolean launchInternalActivities(TaskStackBuilder taskStackBuilder, Context context) {
        return internal().launchActivities(taskStackBuilder, context);
    }

    public static boolean launchInternalActivityForResult(Intent intent, int i, Activity activity) {
        return internal().launchActivityForResult(intent, i, activity);
    }

    public static boolean launchInternalActivityForResult(Intent intent, int i, Fragment fragment) {
        return internal().launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchInternalService(Intent intent, Context context) {
        return internal().launchService(intent, context);
    }

    @Nullable
    public static ComponentName launchInternalForegroundService(Intent intent, Context context) {
        return internal().launchForegroundService(intent, context);
    }

    public static boolean bindInternalService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return internal().bindToService(intent, serviceConnection, i, context);
    }

    public static boolean stopInternalService(Intent intent, Context context) {
        return internal().stopService(intent, context);
    }

    public static boolean sendInternalBroadcast(Intent intent, Context context) {
        return internal().sendBroadcast(intent, context);
    }

    public static boolean sendInternalBroadcast(Intent intent, Context context, String str) {
        return internal().sendBroadcast(intent, context, str);
    }

    public static boolean launchSameKeyActivity(Intent intent, Context context) {
        return sameKey().launchActivity(intent, context);
    }

    public static boolean launchSameKeyActivity(Intent intent, Bundle bundle, Context context) {
        return sameKey().launchActivity(intent, bundle, context);
    }

    public static boolean launchSameKeyActivityForResult(Intent intent, int i, Activity activity) {
        return sameKey().launchActivityForResult(intent, i, activity);
    }

    public static boolean launchSameKeyActivityForResult(Intent intent, int i, Fragment fragment) {
        return sameKey().launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchSameKeyService(Intent intent, Context context) {
        return sameKey().launchService(intent, context);
    }

    @Nullable
    public static ComponentName launchSameKeyForegroundService(Intent intent, Context context) {
        return sameKey().launchForegroundService(intent, context);
    }

    public static boolean bindSameKeyService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return sameKey().bindToService(intent, serviceConnection, i, context);
    }

    public static boolean stopSameKeyService(Intent intent, Context context) {
        return sameKey().stopService(intent, context);
    }

    public static boolean sendSameKeyBroadcast(Intent intent, Context context) {
        return sameKey().sendBroadcast(intent, context);
    }

    public static boolean sendSameKeyBroadcast(Intent intent, Context context, String str) {
        return sameKey().sendBroadcast(intent, context, str);
    }

    public static boolean sendSameKeyOrderedBroadcast(Intent intent, Context context, @Nullable String str, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String str2, @Nullable Bundle bundle) {
        return sameKey().sendOrderedBroadcast(intent, context, str, broadcastReceiver, handler, i, str2, bundle);
    }

    public static boolean launchFamilyActivity(Intent intent, Context context) {
        return family().launchActivity(intent, context);
    }

    public static boolean launchFamilyActivityForResult(Intent intent, int i, Activity activity) {
        return family().launchActivityForResult(intent, i, activity);
    }

    public static boolean launchFamilyActivityForResult(Intent intent, int i, Fragment fragment) {
        return family().launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchFamilyService(Intent intent, Context context) {
        return family().launchService(intent, context);
    }

    @Nullable
    public static ComponentName launchFamilyForegroundService(Intent intent, Context context) {
        return family().launchForegroundService(intent, context);
    }

    public static boolean bindFamilyService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return family().bindToService(intent, serviceConnection, i, context);
    }

    public static boolean sendFamilyBroadcast(Intent intent, Context context) {
        return family().sendBroadcast(intent, context);
    }

    public static boolean sendFamilyBroadcast(Intent intent, Context context, String str) {
        return family().sendBroadcast(intent, context, str);
    }

    @Deprecated
    public static boolean launchExternalActivity(Intent intent, Context context) {
        return external().launchActivity(intent, context);
    }

    @Deprecated
    public static boolean launchExternalActivityForResult(Intent intent, int i, Activity activity) {
        return external().launchActivityForResult(intent, i, activity);
    }

    @Deprecated
    public static boolean launchExternalActivityForResult(Intent intent, int i, Fragment fragment) {
        return external().launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    @Deprecated
    public static ComponentName launchExternalService(Intent intent, Context context) {
        return external().launchService(intent, context);
    }

    public static boolean bindExternalService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return external().bindToService(intent, serviceConnection, i, context);
    }

    public static boolean launchThirdPartyActivity(Intent intent, Context context) {
        return thirdParty().launchActivity(intent, context);
    }

    public static boolean launchThirdPartyActivity(Intent intent, Bundle bundle, Context context) {
        return thirdParty().launchActivity(intent, bundle, context);
    }

    public static boolean launchThirdPartyActivityForResult(Intent intent, int i, Activity activity) {
        return thirdParty().launchActivityForResult(intent, i, activity);
    }

    public static boolean launchThirdPartyActivityForResult(Intent intent, int i, Fragment fragment) {
        return thirdParty().launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchThirdPartyService(Intent intent, Context context) {
        return thirdParty().launchService(intent, context);
    }

    @Nullable
    public static ComponentName launchThirdPartyForegroundService(Intent intent, Context context) {
        return thirdParty().launchForegroundService(intent, context);
    }

    public static boolean bindThirdPartyService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return thirdParty().bindToService(intent, serviceConnection, i, context);
    }

    public static boolean launchAnyPublicActivity(Intent intent, Context context) {
        return onlyPublic().launchActivity(intent, context);
    }

    public static boolean launchAnyPublicActivityForResult(Intent intent, int i, Activity activity) {
        return onlyPublic().launchActivityForResult(intent, i, activity);
    }

    public static boolean launchAnyPublicActivityForResult(Intent intent, int i, Fragment fragment) {
        return onlyPublic().launchActivityForResult(intent, i, fragment);
    }

    @Nullable
    public static ComponentName launchAnyPublicService(Intent intent, Context context) {
        return onlyPublic().launchService(intent, context);
    }

    public static boolean bindAnyPublicService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return onlyPublic().bindToService(intent, serviceConnection, i, context);
    }

    public static boolean sendAnyBroadcast(Intent intent, Context context) {
        return onlyPublic().sendBroadcast(intent, context);
    }

    @Deprecated
    public static boolean launchAnyActivity_UNSAFE(Intent intent, Context context) {
        return any_UNSAFE().launchActivity(intent, context);
    }

    @Nullable
    @Deprecated
    public static ComponentName launchAnyService_UNSAFE(Intent intent, Context context) {
        return any_UNSAFE().launchService(intent, context);
    }

    public static boolean bindAnyService_UNSAFE(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        return any_UNSAFE().bindToService(intent, serviceConnection, i, context);
    }

    private static ScopedIntentLauncher internal() {
        return SecureContextHelper.get().internal();
    }

    private static ScopedIntentLauncher sameKey() {
        return SecureContextHelper.get().sameKey();
    }

    private static ScopedIntentLauncher family() {
        return SecureContextHelper.get().family();
    }

    private static ScopedIntentLauncher external() {
        return SecureContextHelper.get().external();
    }

    private static ScopedIntentLauncher thirdParty() {
        return SecureContextHelper.get().thirdParty();
    }

    private static ScopedIntentLauncher onlyPublic() {
        return SecureContextHelper.get().accessibleByAnyApp();
    }

    @SuppressLint({"BadMethodUse-com.facebook.secure.context.SecureContextHelper.any_UNSAFE"})
    private static ScopedIntentLauncher any_UNSAFE() {
        return SecureContextHelper.get().any_UNSAFE();
    }
}
