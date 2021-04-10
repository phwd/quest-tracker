package com.oculus.panelapp.messenger.memory;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.acra.AppComponentStats;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.messenger.BaseMessengerTabletPanelApp;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.concurrent.Callable;

public class MessengerMemoryManager implements AndroidPanelApp.PanelFrameCallback, ComponentCallbacks2 {
    public static final long FIRST_MEMORY_MEASUREMENT_MILLIS = 3000;
    public static final long MEMORY_MEASUREMENT_RATE_MILLIS = 60179;
    public static final String TAG = LoggingUtil.tag(MessengerMemoryManager.class);
    public final Context mContext;
    public long mLastMemoryMeasurementChecked;
    public final BaseMessengerTabletPanelApp mPanelApp;

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int i) {
        if (i == 5) {
        }
    }

    private void checkMemory() {
        ThreadExecutor.INSTANCE.execute(new Callable() {
            /* class com.oculus.panelapp.messenger.memory.$$Lambda$MessengerMemoryManager$_bZUJNbSEpad0bypgbMrCf3WxO02 */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MessengerMemoryManager.this.lambda$checkMemory$0$MessengerMemoryManager();
            }
        });
    }

    public void destroy() {
        this.mPanelApp.removePanelFrameCallback(this);
        this.mContext.unregisterComponentCallbacks(this);
    }

    public /* synthetic */ Object lambda$checkMemory$0$MessengerMemoryManager() throws Exception {
        ((ActivityManager) this.mContext.getSystemService(AppComponentStats.TAG_ACTIVITY)).getMemoryInfo(new ActivityManager.MemoryInfo());
        return null;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long j = inputFrame.mTimeMillis;
        long j2 = this.mLastMemoryMeasurementChecked;
        if (j2 == 0) {
            this.mLastMemoryMeasurementChecked = (j - MEMORY_MEASUREMENT_RATE_MILLIS) + FIRST_MEMORY_MEASUREMENT_MILLIS;
        } else if (j - j2 > MEMORY_MEASUREMENT_RATE_MILLIS) {
            this.mLastMemoryMeasurementChecked = j;
            checkMemory();
        }
    }

    public MessengerMemoryManager(BaseMessengerTabletPanelApp baseMessengerTabletPanelApp) {
        this.mPanelApp = baseMessengerTabletPanelApp;
        this.mContext = baseMessengerTabletPanelApp.mContext;
        baseMessengerTabletPanelApp.addPanelFrameCallback(this);
        this.mContext.registerComponentCallbacks(this);
    }
}
