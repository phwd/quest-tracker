package com.oculus.vrapi;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindServiceWrapper {
    static final int ANDROID_M = 23;
    static final int ANDROID_N = 24;
    static Class cActivityManagerNative;
    static Method cActivityManagerNative_getDefault;
    static Class cActivityThread;
    static Method cActivityThread_getApplicationThread;
    static Class cContextImpl;
    static Method cContextImpl_getActivityToken;
    static Method cContextImpl_getOpPackageName;
    static Field cContextImpl_mMainThread;
    static Field cContextImpl_mPackageInfo;
    static Method cContextImpl_validateServiceIntent;
    static Method cContext_bindServiceAsUser;
    static Class cIActivityManager;
    static Method cIActivityManager_bindService;
    static Class cIApplicationThread;
    static Class cIServiceConnection;
    static Method cIntent_prepareToLeaveProcess;
    static Class cLoadedApk;
    static Method cLoadedApk_getServiceDispatcher;
    static Method cProcess_myUserHandle;
    static Method cUserHandle_getIdentifier;

    public static void init() throws NoSuchMethodException, ClassNotFoundException, NoSuchFieldException {
        cProcess_myUserHandle = Process.class.getDeclaredMethod("myUserHandle", new Class[0]);
        cContextImpl = Class.forName("android.app.ContextImpl");
        if (Build.VERSION.SDK_INT >= ANDROID_N) {
            cContext_bindServiceAsUser = Context.class.getMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, Handler.class, UserHandle.class);
            return;
        }
        cIServiceConnection = Class.forName("android.app.IServiceConnection");
        cContextImpl_mPackageInfo = cContextImpl.getDeclaredField("mPackageInfo");
        cContextImpl_mPackageInfo.setAccessible(true);
        cContextImpl_mMainThread = cContextImpl.getDeclaredField("mMainThread");
        cContextImpl_mMainThread.setAccessible(true);
        cContextImpl_validateServiceIntent = cContextImpl.getDeclaredMethod("validateServiceIntent", Intent.class);
        cContextImpl_validateServiceIntent.setAccessible(true);
        cContextImpl_getActivityToken = cContextImpl.getDeclaredMethod("getActivityToken", new Class[0]);
        cContextImpl_getActivityToken.setAccessible(true);
        cContextImpl_getOpPackageName = cContextImpl.getDeclaredMethod("getOpPackageName", new Class[0]);
        cContextImpl_getOpPackageName.setAccessible(true);
        cLoadedApk = Class.forName("android.app.LoadedApk");
        cLoadedApk_getServiceDispatcher = cLoadedApk.getDeclaredMethod("getServiceDispatcher", ServiceConnection.class, Context.class, Handler.class, Integer.TYPE);
        cIntent_prepareToLeaveProcess = Intent.class.getDeclaredMethod("prepareToLeaveProcess", new Class[0]);
        cActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
        cActivityManagerNative_getDefault = cActivityManagerNative.getDeclaredMethod("getDefault", new Class[0]);
        cActivityThread = Class.forName("android.app.ActivityThread");
        cActivityThread_getApplicationThread = cActivityThread.getDeclaredMethod("getApplicationThread", new Class[0]);
        cIApplicationThread = Class.forName("android.app.IApplicationThread");
        cIActivityManager = Class.forName("android.app.IActivityManager");
        if (Build.VERSION.SDK_INT < ANDROID_M) {
            cIActivityManager_bindService = cIActivityManager.getDeclaredMethod("bindService", cIApplicationThread, IBinder.class, Intent.class, String.class, cIServiceConnection, Integer.TYPE, Integer.TYPE);
        } else {
            cIActivityManager_bindService = cIActivityManager.getDeclaredMethod("bindService", cIApplicationThread, IBinder.class, Intent.class, String.class, cIServiceConnection, Integer.TYPE, String.class, Integer.TYPE);
        }
        cUserHandle_getIdentifier = UserHandle.class.getDeclaredMethod("getIdentifier", new Class[0]);
    }

    private static Context getContextImpl(Context context) {
        while (!cContextImpl.isAssignableFrom(context.getClass())) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
                continue;
            } else {
                context = null;
                continue;
            }
            if (context == null) {
                throw new RuntimeException("Failed to find ContextImpl for context object");
            }
        }
        return context;
    }

    public static boolean bindServiceWithHandler(Context context, Intent service, ServiceConnection conn, int flags, Handler handler) throws IllegalAccessException, RuntimeException, IllegalArgumentException, InvocationTargetException {
        int res;
        Context contextImpl = getContextImpl(context);
        UserHandle user = (UserHandle) cProcess_myUserHandle.invoke(null, new Object[0]);
        if (Build.VERSION.SDK_INT >= ANDROID_N) {
            return ((Boolean) cContext_bindServiceAsUser.invoke(contextImpl, service, conn, Integer.valueOf(flags), handler, user)).booleanValue();
        }
        Object mPackageInfo = cContextImpl_mPackageInfo.get(contextImpl);
        if (conn == null) {
            throw new IllegalArgumentException("connection is null");
        } else if (mPackageInfo != null) {
            Object serviceDispatcher = cLoadedApk_getServiceDispatcher.invoke(mPackageInfo, conn, context, handler, Integer.valueOf(flags));
            cContextImpl_validateServiceIntent.invoke(contextImpl, service);
            IBinder token = (IBinder) cContextImpl_getActivityToken.invoke(contextImpl, new Object[0]);
            cIntent_prepareToLeaveProcess.invoke(service, new Object[0]);
            Object iActivityManager = cActivityManagerNative_getDefault.invoke(null, new Object[0]);
            String opPackageName = (String) cContextImpl_getOpPackageName.invoke(contextImpl, new Object[0]);
            int userHandleIdentifier = ((Integer) cUserHandle_getIdentifier.invoke(user, new Object[0])).intValue();
            Object mMainThread = cContextImpl_mMainThread.get(contextImpl);
            if (Build.VERSION.SDK_INT < ANDROID_M) {
                res = ((Integer) cIActivityManager_bindService.invoke(iActivityManager, cActivityThread_getApplicationThread.invoke(mMainThread, new Object[0]), token, service, service.resolveTypeIfNeeded(context.getContentResolver()), serviceDispatcher, Integer.valueOf(flags), Integer.valueOf(userHandleIdentifier))).intValue();
            } else {
                res = ((Integer) cIActivityManager_bindService.invoke(iActivityManager, cActivityThread_getApplicationThread.invoke(mMainThread, new Object[0]), token, service, service.resolveTypeIfNeeded(context.getContentResolver()), serviceDispatcher, Integer.valueOf(flags), opPackageName, Integer.valueOf(userHandleIdentifier))).intValue();
            }
            if (res < 0) {
                throw new SecurityException("Not allowed to bind to service " + service);
            } else if (res != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException("Not supported in system context");
        }
    }
}
