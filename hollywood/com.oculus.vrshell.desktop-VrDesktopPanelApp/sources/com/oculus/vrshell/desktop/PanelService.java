package com.oculus.vrshell.desktop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;
import android.view.Surface;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Map;

public class PanelService extends com.oculus.vrshell.panelservice.PanelService {
    private static final String ACTION_SHOW_KEYBOARD = "com.oculus.show_keyboard";
    private static final String TAG = "VrDesktopPanelService";
    private BroadcastReceiver mKeyboardEventReceiver = new BroadcastReceiver() {
        /* class com.oculus.vrshell.desktop.PanelService.AnonymousClass1 */

        public void onReceive(Context context, Intent intent) {
            if (PanelService.ACTION_SHOW_KEYBOARD.equals(intent.getAction())) {
                PanelService.this.mPanelApps.forEach($$Lambda$PanelService$1$2GLFNa1DvX1qVMwt9qq_ciD_Us.INSTANCE);
            }
        }

        static /* synthetic */ void lambda$onReceive$0(WeakReference appRef) {
            PanelApp app = (PanelApp) appRef.get();
            if (app != null) {
                app.setKeyboardVisibility(true);
            }
        }
    };
    private final LinkedList<WeakReference<PanelApp>> mPanelApps = new LinkedList<>();
    private final LinkedList<WeakReference<PanelApp>> mTokenPending = new LinkedList<>();

    static {
        System.loadLibrary("vrdesktop_jni");
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        super.onCreate();
        registerReceiverAsUser(this.mKeyboardEventReceiver, UserHandle.ALL, new IntentFilter(ACTION_SHOW_KEYBOARD), null, null);
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onDestroy() {
        unregisterReceiver(this.mKeyboardEventReceiver);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface panelSurface, Map<String, Surface> map, Bundle panelBundle) {
        PanelApp app = new PanelApp(this, panelSurface, panelBundle);
        WeakReference<PanelApp> appRef = new WeakReference<>(app);
        this.mPanelApps.add(appRef);
        this.mTokenPending.add(appRef);
        Log.d(TAG, "createNativePanelAppInstance: app=" + app);
        return app.getNativePointer();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onShellTokenReady(IBinder token) {
        WeakReference<PanelApp> appRef = this.mTokenPending.pollFirst();
        if (appRef != null) {
            PanelApp app = appRef.get();
            if (app != null) {
                Log.d(TAG, "onShellTokenReady: app=" + app);
                app.onShellTokenReady(token);
                return;
            }
            return;
        }
        throw new RuntimeException("onShellTokenReady called before PanelApp is created");
    }
}
