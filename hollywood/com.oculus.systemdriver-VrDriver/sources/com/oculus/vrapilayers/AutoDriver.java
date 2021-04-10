package com.oculus.vrapilayers;

import android.content.Context;
import android.util.Log;

public class AutoDriver {
    private static final String TAG = AutoDriver.class.getSimpleName();
    private static boolean sLayerStarted = false;

    private static native void nativeBeginScene(int i);

    private static native void nativeEndScene();

    private static native boolean nativeInitialize(String str, int i);

    private static native void nativeShutdown();

    /* access modifiers changed from: private */
    public static native void nativeShutdownHook();

    private static native void nativeStartLayer(long j, long j2, ClassLoader classLoader);

    private static class ShutdownHook extends Thread {
        private ShutdownHook() {
        }

        public void run() {
            if (AutoDriver.sLayerStarted) {
                Log.d(AutoDriver.TAG, "ShutdownHook");
                AutoDriver.nativeShutdownHook();
                boolean unused = AutoDriver.sLayerStarted = false;
            }
        }
    }

    public static boolean AutoDriverAppEnabled(Context appContext) {
        return appContext.getPackageName().equals(AndroidSystemProperties.getSystemPropertyString("debug.oculus.autoDriverApp", null));
    }

    public static boolean AutoDriverBroadcastsEnabled(Context appContext) {
        String autoDriverController = AndroidSystemProperties.getSystemPropertyString("debug.oculus.autoDriverController", null);
        return "2".equals(autoDriverController) || "AndroidBroadcasts".equalsIgnoreCase(autoDriverController);
    }

    public static void StartLayer(long vrapi_fnptr, long vrapi_priv_fnptr, Context appContext) {
        if (AutoDriverAppEnabled(appContext)) {
            if (sLayerStarted) {
                Log.w(TAG, "StartLayer: libautodriver.so already loaded");
                return;
            }
            Log.d(TAG, "StartLayer: Loading libautodriver.so");
            System.loadLibrary("autodriver");
            nativeStartLayer(vrapi_fnptr, vrapi_priv_fnptr, AutoDriver.class.getClassLoader());
            if (AutoDriverBroadcastsEnabled(appContext)) {
                Log.d(TAG, "StartLayer: Registering AutoDriverBroadcastReceiver");
                AutoDriverBroadcastReceiver.RegisterReceiver(appContext);
            }
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
            sLayerStarted = true;
        }
    }

    public static boolean Initialize(String name, int mode) {
        String str = TAG;
        Log.d(str, "Initialize: " + name + " " + mode);
        if (sLayerStarted) {
            return nativeInitialize(name, mode);
        }
        Log.e(TAG, "Layer not started");
        return false;
    }

    public static void BeginScene(int sceneID) {
        String str = TAG;
        Log.d(str, "BeginScene: " + sceneID);
        if (!sLayerStarted) {
            Log.e(TAG, "Layer not started");
        } else {
            nativeBeginScene(sceneID);
        }
    }

    public static void EndScene() {
        Log.d(TAG, "EndScene");
        if (!sLayerStarted) {
            Log.e(TAG, "Layer not started");
        } else {
            nativeEndScene();
        }
    }

    public static void Shutdown() {
        Log.d(TAG, "Shutdown");
        if (!sLayerStarted) {
            Log.e(TAG, "Layer not started");
        } else {
            nativeShutdown();
        }
    }
}
