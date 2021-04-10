package com.oculus.vrshell.home;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.vrshell.panel.PanelServiceHelpers;
import java.lang.ref.WeakReference;

public abstract class StartRenderingOverlaysService extends Service {
    public static final String CREATE_PANEL_APP_KEY_BUNDLE = "panelBundle";
    public static final String CREATE_PANEL_APP_KEY_HEIGHT = "panelHeight";
    public static final String CREATE_PANEL_APP_KEY_SURFACE = "panelSurface";
    public static final String CREATE_PANEL_APP_KEY_WIDTH = "panelWidth";
    public static final int MSG_CREATE_OR_UPDATE_PANEL_APP = 2;
    private static final String TAG = "StartRenderingOverlays";
    int mHeight = -1;
    int mWidth = -1;
    protected Messenger messenger = null;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeCreatePanelApp(Surface surface, String[] strArr, int i);

    private native void nativeLoadPanelAppLibrary(String str);

    public StartRenderingOverlaysService(String libraryName) {
        System.loadLibrary(libraryName);
        nativeLoadPanelAppLibrary(libraryName);
    }

    private static class PanelMessageHandler extends Handler {
        private final WeakReference<StartRenderingOverlaysService> service;

        PanelMessageHandler(StartRenderingOverlaysService cps) {
            this.service = new WeakReference<>(cps);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Bundle b = msg.getData();
                    Surface panelSurface = (Surface) b.getParcelable("panelSurface");
                    if (panelSurface == null) {
                        Log.i(StartRenderingOverlaysService.TAG, "Received invalid surface, aborting");
                        return;
                    }
                    Bundle panelBundle = b.getBundle("panelBundle");
                    PanelServiceHelpers.waitForDebuggerIfNeeded(panelBundle);
                    String[] panelEnv = PanelServiceHelpers.envArrayFromBundle(panelBundle);
                    int sharedMemoryFd = -1;
                    ParcelFileDescriptor pfd = (ParcelFileDescriptor) b.getParcelable("sharedMemoryFd");
                    if (pfd != null) {
                        sharedMemoryFd = pfd.detachFd();
                    }
                    Log.i(StartRenderingOverlaysService.TAG, String.format("Creating panel app with environment %s", PanelServiceHelpers.envArrayToString(panelEnv)));
                    this.service.get().setDimensions(b.getInt(StartRenderingOverlaysService.CREATE_PANEL_APP_KEY_WIDTH), b.getInt(StartRenderingOverlaysService.CREATE_PANEL_APP_KEY_HEIGHT));
                    this.service.get().nativeCreatePanelApp(panelSurface, panelEnv, sharedMemoryFd);
                    return;
                default:
                    super.handleMessage(msg);
                    return;
            }
        }
    }

    public void setDimensions(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
        this.messenger = new Messenger(new PanelMessageHandler(this));
    }

    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, String.format("onStartCommand(%x, %d", Integer.valueOf(flags), Integer.valueOf(startId)));
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        return this.messenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    public int getPanelWidth() {
        return this.mWidth;
    }

    public int getPanelHeight() {
        return this.mHeight;
    }
}
