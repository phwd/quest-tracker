package com.oculus.os;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.IProcessObserver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import java.util.List;
import oculus.internal.ActivityUtils;
import oculus.internal.Constants;
import oculus.internal.IOculusWindowManager;
import oculus.internal.ISystemKeyEventHandler;
import oculus.internal.IWindowLayoutObserver;
import oculus.internal.PanelAppUtils;

public class ActivityManagerUtils {
    public static final String ACTION_THIRD_PARTY_PACKAGE_STARTED = "com.oculus.action.THIRD_PARTY_PACKAGE_STARTED";
    public static final String PERMISSION_RECEIVE_THIRD_PARTY_PACKAGE_STARTED = "com.oculus.permission.RECEIVE_THIRD_PARTY_PACKAGE_STARTED";
    private static final String TAG = "ActivityManagerUtils";
    private ForegroundActivityObserverWrapper mForegroundActivityObserverWrapper;
    private SystemKeyEventHandlerWrapper mSystemKeyEventHandlerWrapper;
    private WindowLayoutObserverWrapper mWindowLayoutObserverWrapper;

    public interface ForegroundActivityObserver {
        void onForegroundActivitiesChanged(int i, int i2, boolean z);
    }

    public interface SystemKeyEventHandler {
        boolean handleSystemKeyEvent(int i, int i2);
    }

    public String getForegroundApp(Context context) {
        List<ActivityManager.RunningTaskInfo> tasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (tasks == null || tasks.size() < 1) {
            Log.w(TAG, "Couldn't get tasks");
            return null;
        }
        ActivityManager.RunningTaskInfo taskInfo = tasks.get(0);
        if (taskInfo.topActivity != null) {
            return taskInfo.topActivity.getPackageName();
        }
        Log.w(TAG, "Top running task does not have information on activity");
        return null;
    }

    public boolean setWindowLayoutObserver(WindowLayoutObserver observer, Handler handler) {
        if (this.mWindowLayoutObserverWrapper != null) {
            clearWindowLayoutObserver();
        }
        this.mWindowLayoutObserverWrapper = new WindowLayoutObserverWrapper(handler, observer);
        IOculusWindowManager wm = IOculusWindowManager.Stub.asInterface(ServiceManager.getService(Constants.OCULUS_WINDOW_MANAGER_SERVICE));
        if (wm == null) {
            Log.w(TAG, "OculusWindowManager is not found");
            return false;
        }
        try {
            return wm.registerWindowLayoutObserver(this.mWindowLayoutObserverWrapper);
        } catch (Exception e) {
            Log.e(TAG, "Couldn't set window layout observer", e);
            return false;
        }
    }

    public boolean clearWindowLayoutObserver() {
        if (this.mWindowLayoutObserverWrapper == null) {
            Log.w(TAG, "Window layout observer does not exist");
            return false;
        }
        try {
            IOculusWindowManager.Stub.asInterface(ServiceManager.getService(Constants.OCULUS_WINDOW_MANAGER_SERVICE)).unregisterWindowLayoutObserver(this.mWindowLayoutObserverWrapper);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Couldn't clear window layout observer", e);
            return false;
        }
    }

    public boolean setIsForegroundPanelService(boolean value, ComponentName component) {
        return PanelAppUtils.setIsForegroundPanelService(value, component);
    }

    public boolean startActivityFromRecents(Intent intent, Bundle options) {
        return ActivityUtils.startActivityFromRecents(intent, options);
    }

    public interface WindowLayoutObserver {
        @Deprecated
        void onFocusedWindowChanged(IBinder iBinder, int i);

        default void onFocusedWindowChanged(IBinder windowToken, int owningUid, int owningPid) {
            onFocusedWindowChanged(windowToken, owningUid);
        }
    }

    /* access modifiers changed from: private */
    public static class WindowLayoutObserverWrapper extends IWindowLayoutObserver.Stub {
        private final WindowLayoutObserver mCallback;
        private final Handler mHandler;

        private WindowLayoutObserverWrapper(Handler handler, WindowLayoutObserver callback) {
            this.mHandler = handler;
            this.mCallback = callback;
        }

        @Override // oculus.internal.IWindowLayoutObserver
        public void onFocusedWindowChanged(final IBinder windowToken, final int owningUid, final int owningPid) {
            this.mHandler.post(new Runnable() {
                /* class com.oculus.os.ActivityManagerUtils.WindowLayoutObserverWrapper.AnonymousClass1 */

                public void run() {
                    WindowLayoutObserverWrapper.this.mCallback.onFocusedWindowChanged(windowToken, owningUid, owningPid);
                }
            });
        }
    }

    public boolean setForegroundActivityObserver(ForegroundActivityObserver observer, Handler handler) {
        if (this.mForegroundActivityObserverWrapper != null) {
            clearForegroundActivityObserver();
        }
        this.mForegroundActivityObserverWrapper = new ForegroundActivityObserverWrapper(observer, handler);
        try {
            ActivityManagerNative.getDefault().registerProcessObserver(this.mForegroundActivityObserverWrapper);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Exception registering process observer", e);
            return false;
        }
    }

    public boolean clearForegroundActivityObserver() {
        if (this.mForegroundActivityObserverWrapper == null) {
            Log.w(TAG, "Foreground activity observer is not set");
            return false;
        }
        try {
            ActivityManagerNative.getDefault().unregisterProcessObserver(this.mForegroundActivityObserverWrapper);
            this.mForegroundActivityObserverWrapper = null;
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Exception unregistering process observer", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class ForegroundActivityObserverWrapper extends IProcessObserver.Stub {
        private final ForegroundActivityObserver mCallback;
        private final Handler mHandler;

        private ForegroundActivityObserverWrapper(ForegroundActivityObserver callback, Handler handler) {
            this.mCallback = callback;
            this.mHandler = handler;
        }

        public void onForegroundActivitiesChanged(final int pid, final int uid, final boolean foregroundActivities) {
            this.mHandler.post(new Runnable() {
                /* class com.oculus.os.ActivityManagerUtils.ForegroundActivityObserverWrapper.AnonymousClass1 */

                public void run() {
                    ForegroundActivityObserverWrapper.this.mCallback.onForegroundActivitiesChanged(pid, uid, foregroundActivities);
                }
            });
        }

        public void onProcessStateChanged(int pid, int uid, int procState) {
        }

        public void onForegroundServicesChanged(int pid, int uid, int serviceTypes) {
        }

        public void onProcessDied(int pid, int uid) {
        }
    }

    public boolean setSystemKeyEventHandler(SystemKeyEventHandler handler) {
        if (this.mSystemKeyEventHandlerWrapper != null) {
            clearSystemKeyEventHandler();
        }
        this.mSystemKeyEventHandlerWrapper = new SystemKeyEventHandlerWrapper(handler);
        IOculusWindowManager wm = IOculusWindowManager.Stub.asInterface(ServiceManager.getService(Constants.OCULUS_WINDOW_MANAGER_SERVICE));
        if (wm == null) {
            Log.w(TAG, "OculusWindowManager is not found");
            return false;
        }
        try {
            return wm.registerSystemKeyEventHandler(this.mSystemKeyEventHandlerWrapper);
        } catch (Exception e) {
            Log.e(TAG, "Couldn't register SystemKeyEvent handler", e);
            return false;
        }
    }

    public boolean clearSystemKeyEventHandler() {
        if (this.mSystemKeyEventHandlerWrapper == null) {
            Log.w(TAG, "System key event handler is not set");
            return false;
        }
        try {
            return IOculusWindowManager.Stub.asInterface(ServiceManager.getService(Constants.OCULUS_WINDOW_MANAGER_SERVICE)).unregisterSystemKeyEventHandler(this.mSystemKeyEventHandlerWrapper);
        } catch (Exception e) {
            Log.e(TAG, "Couldn't clear SystemKeyEvent handler", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class SystemKeyEventHandlerWrapper extends ISystemKeyEventHandler.Stub {
        private final SystemKeyEventHandler mHandler;

        private SystemKeyEventHandlerWrapper(SystemKeyEventHandler handler) {
            this.mHandler = handler;
        }

        @Override // oculus.internal.ISystemKeyEventHandler
        public boolean handleSystemKeyEvent(int keyCode, int action) {
            return this.mHandler.handleSystemKeyEvent(keyCode, action);
        }
    }

    public static UserHandle getCurrentUser() {
        return UserHandle.of(ActivityManager.getCurrentUser());
    }

    public static int getUserIdFromHandle(UserHandle handle) {
        return handle.getIdentifier();
    }

    public static UserHandle getUserHandleForUserId(int userId) {
        return UserHandle.of(userId);
    }
}
