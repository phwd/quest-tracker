package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;

public class ActivityManagerUtils {
    public static final String ACTION_THIRD_PARTY_PACKAGE_STARTED = "com.oculus.action.THIRD_PARTY_PACKAGE_STARTED";
    public static final String PERMISSION_RECEIVE_THIRD_PARTY_PACKAGE_STARTED = "com.oculus.permission.RECEIVE_THIRD_PARTY_PACKAGE_STARTED";

    public interface ForegroundActivityObserver {
        void onForegroundActivitiesChanged(int i, int i2, boolean z);
    }

    public interface SystemKeyEventHandler {
        boolean handleSystemKeyEvent(int i, int i2);
    }

    public interface WindowLayoutObserver {
        @Deprecated
        void onFocusedWindowChanged(IBinder iBinder, int i);

        void onFocusedWindowChanged(IBinder iBinder, int i, int i2);
    }

    public ActivityManagerUtils() {
        throw new RuntimeException("Stub!");
    }

    public String getForegroundApp(Context context) {
        throw new RuntimeException("Stub!");
    }

    public boolean setWindowLayoutObserver(WindowLayoutObserver observer, Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public boolean clearWindowLayoutObserver() {
        throw new RuntimeException("Stub!");
    }

    public boolean setIsForegroundPanelService(boolean value, ComponentName component) {
        throw new RuntimeException("Stub!");
    }

    public boolean startActivityFromRecents(Intent intent, Bundle options) {
        throw new RuntimeException("Stub!");
    }

    public boolean setForegroundActivityObserver(ForegroundActivityObserver observer, Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public boolean clearForegroundActivityObserver() {
        throw new RuntimeException("Stub!");
    }

    public boolean setSystemKeyEventHandler(SystemKeyEventHandler handler) {
        throw new RuntimeException("Stub!");
    }

    public boolean clearSystemKeyEventHandler() {
        throw new RuntimeException("Stub!");
    }

    public static UserHandle getCurrentUser() {
        throw new RuntimeException("Stub!");
    }

    public static int getUserIdFromHandle(UserHandle handle) {
        throw new RuntimeException("Stub!");
    }

    public static UserHandle getUserHandleForUserId(int userId) {
        throw new RuntimeException("Stub!");
    }
}
