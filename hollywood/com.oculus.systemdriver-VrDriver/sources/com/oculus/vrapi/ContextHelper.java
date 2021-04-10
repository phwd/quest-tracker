package com.oculus.vrapi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import com.oculus.systemdriver.BuildConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ContextHelper {
    private static final UserHandle ALL_USERS;
    private static final UserHandle CURRENT_USER;
    private static final String TAG = ContextHelper.class.getSimpleName();
    private static final Method sMethodRegisterReceiverAsUser;
    private static final Method sMethodSendBroadcastAsUser;
    private static final Method sMethodStartServiceAsUser;

    static {
        try {
            CURRENT_USER = (UserHandle) UserHandle.class.getField("CURRENT").get(null);
            ALL_USERS = (UserHandle) UserHandle.class.getField("ALL").get(null);
            sMethodStartServiceAsUser = Context.class.getMethod("startServiceAsUser", Intent.class, UserHandle.class);
            sMethodSendBroadcastAsUser = Context.class.getMethod("sendBroadcastAsUser", Intent.class, UserHandle.class);
            sMethodRegisterReceiverAsUser = Context.class.getMethod("registerReceiverAsUser", BroadcastReceiver.class, UserHandle.class, IntentFilter.class, String.class, Handler.class);
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runOnUiThread(Context context, Runnable action) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(action);
        } else {
            new Handler(Looper.getMainLooper()).post(action);
        }
    }

    public static void startActivityWithoutTransitionAnimation(Context context, Intent intent) {
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    public static void finishActivityWithoutTransitionAnimation(final Context context, final boolean force) {
        if (context instanceof Activity) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* class com.oculus.vrapi.ContextHelper.AnonymousClass1 */

                public void run() {
                    Activity activity = (Activity) context;
                    if (!ContextHelper.shouldSkipFinish(activity) || force) {
                        String str = ContextHelper.TAG;
                        Log.d(str, "Calling finish() on " + activity);
                        activity.finish();
                        activity.overridePendingTransition(0, 0);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static boolean shouldSkipFinish(Activity activity) {
        String str = TAG;
        Log.d(str, "shouldSkipFinish: activity=" + activity);
        try {
            boolean resumed = ((Boolean) Activity.class.getMethod("isResumed", new Class[0]).invoke(activity, new Object[0])).booleanValue();
            String str2 = TAG;
            Log.d(str2, "activity=" + activity + ", resumed=" + resumed);
            return resumed;
        } catch (Exception e) {
            Log.d(TAG, "Failed to call isResumed using reflection", e);
            return false;
        }
    }

    private static boolean isSystemDriverContext(Context context) {
        return BuildConfig.APPLICATION_ID.equals(context.getPackageName());
    }

    public static void startServiceAsCurrentUser(Context context, Intent intent) {
        try {
            sMethodStartServiceAsUser.invoke(context, intent, CURRENT_USER);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "Failed to call startServiceAsCurrentUser using reflection", e);
        }
    }

    public static void sendBroadcastAsCurrentUser(Context context, Intent intent) {
        if (!isSystemDriverContext(context)) {
            context.sendBroadcast(intent);
            return;
        }
        try {
            sMethodSendBroadcastAsUser.invoke(context, intent, CURRENT_USER);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "Failed to call sendBroadcastAsCurrentUser using reflection", e);
        }
    }

    public static void sendBroadcastToAllUsers(Context context, Intent intent) {
        try {
            sMethodSendBroadcastAsUser.invoke(context, intent, ALL_USERS);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "Failed to call sendBroadcastAsCurrentUser using reflection", e);
        }
    }

    public static void registerReceiverAsAllUsers(Context context, BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission) {
        try {
            sMethodRegisterReceiverAsUser.invoke(context, receiver, ALL_USERS, filter, broadcastPermission, null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "Failed to call registerBroadcastReceiverAsUser using reflection", e);
        }
    }
}
