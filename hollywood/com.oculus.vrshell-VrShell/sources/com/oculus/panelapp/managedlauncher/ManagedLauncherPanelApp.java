package com.oculus.panelapp.managedlauncher;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.InputFrame;
import com.oculus.vrshell.panels.PanelButton;
import java.util.Map;

public final class ManagedLauncherPanelApp extends AndroidPanelApp {
    private static final AndroidPanelLayer.Spec MAIN_LAYER_SPEC = new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 696, 0, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_HEIGHT, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme);
    private static final String OVERRIDE_CONFIG_FILE = "/sdcard/Oculus/demo_apps.config";
    private static String TAG = LoggingUtil.tag(ManagedLauncherPanelApp.class);
    private static final int UNLOCK_TIMER_DURATION = 5000;
    private ManagedLauncherView mMainView;
    private final AndroidPanelApp.PanelFrameCallback unlockTimerFrameCallback = new AndroidPanelApp.PanelFrameCallback() {
        /* class com.oculus.panelapp.managedlauncher.ManagedLauncherPanelApp.AnonymousClass1 */

        @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
        public void onBeginFrame(InputFrame inputFrame) {
            long currentTimeMillis = System.currentTimeMillis();
            if (inputFrame.getButtons() == PanelButton.B.getFlagValue()) {
                if (ManagedLauncherPanelApp.this.unlockTimerStartTime == 0) {
                    ManagedLauncherPanelApp.this.unlockTimerStartTime = currentTimeMillis;
                }
            } else if (ManagedLauncherPanelApp.this.unlockTimerStartTime != 0) {
                ManagedLauncherPanelApp.this.unlockTimerStartTime = 0;
            }
            if (currentTimeMillis - ManagedLauncherPanelApp.this.unlockTimerStartTime > 5000 && ManagedLauncherPanelApp.this.unlockTimerStartTime != 0) {
                ManagedLauncherPanelApp.this.unlockTimerStartTime = 0;
                ManagedLauncherPanelApp.this.unlock();
            }
        }
    };
    private long unlockTimerStartTime;

    private native long nativeCreateInstance(long j, long j2);

    private native void nativeUnlock(long j);

    private native void nativeUnlockGuardian(long j);

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onBackground() {
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onForeground() {
    }

    public ManagedLauncherPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        addPanelFrameCallback(this.unlockTimerFrameCallback);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance((long) MAIN_LAYER_SPEC.width, (long) MAIN_LAYER_SPEC.height);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        return MAIN_LAYER_SPEC;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        String str2 = TAG;
        Log.d(str2, "Inflating view for viewIdentifier = " + i);
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layerName: " + str);
        } else if (this.mMainView == null) {
            this.mMainView = (ManagedLauncherView) LayoutInflater.from(context).inflate(R.layout.managed_launcher_panel, (ViewGroup) null);
            this.mMainView.initialize(this);
            return this.mMainView;
        } else {
            throw new UnsupportedOperationException("trying to recreate main layer!");
        }
    }

    /* access modifiers changed from: package-private */
    public void unlock() {
        nativeUnlock(getNativePointer());
    }

    /* access modifiers changed from: package-private */
    public void unlockGuardian() {
        nativeUnlockGuardian(getNativePointer());
    }
}
