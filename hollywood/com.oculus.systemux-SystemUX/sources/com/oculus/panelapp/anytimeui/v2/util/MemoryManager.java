package com.oculus.panelapp.anytimeui.v2.util;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Debug;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ContentHostView;
import com.oculus.panelapp.anytimeui.v2.tablet.common.HostView;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MemoryManager implements AndroidPanelApp.PanelFrameCallback, ComponentCallbacks2 {
    private static final long FIRST_MEMORY_MEASUREMENT_MILLIS = 3000;
    private static final String LOG_EVENT_MEMORY_MEASUREMENT = "oculus_vrshell_systemux_meminfo";
    private static final long MEMORY_MEASUREMENT_RATE_MILLIS = 60179;
    private static final String TAG = LoggingUtil.tag(MemoryManager.class);
    private static final long VIEW_CACHE_AGGRESSIVE_CLEAR_MAX_AGE_SECS = 0;
    private static final long VIEW_CACHE_CLEAR_RATE_MILLIS = 15091;
    private static final long VIEW_CACHE_PERIODIC_CLEAR_MAX_AGE_SECS = 60;
    private long mLastMemoryMeasurementLogged;
    private long mLastViewCacheCleared = System.currentTimeMillis();
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private ContentHostView mTabletContentView;
    private HostView mTabletView;

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public MemoryManager(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mPanelApp.addPanelFrameCallback(this);
        this.mPanelApp.getContext().registerComponentCallbacks(this);
    }

    public void destroy() {
        this.mPanelApp.removePanelFrameCallback(this);
        this.mPanelApp.getContext().unregisterComponentCallbacks(this);
    }

    public void setTabletView(HostView hostView) {
        this.mTabletView = hostView;
    }

    public void setTabletContentView(ContentHostView contentHostView) {
        this.mTabletContentView = contentHostView;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long timeMillis = inputFrame.getTimeMillis();
        long j = this.mLastMemoryMeasurementLogged;
        if (j == 0) {
            this.mLastMemoryMeasurementLogged = (timeMillis - MEMORY_MEASUREMENT_RATE_MILLIS) + FIRST_MEMORY_MEASUREMENT_MILLIS;
        } else if (timeMillis - j > MEMORY_MEASUREMENT_RATE_MILLIS) {
            this.mLastMemoryMeasurementLogged = timeMillis;
            logMemoryMeasurements();
        }
        if (timeMillis - this.mLastViewCacheCleared > VIEW_CACHE_CLEAR_RATE_MILLIS) {
            this.mLastViewCacheCleared = timeMillis;
            clearViewCache(VIEW_CACHE_PERIODIC_CLEAR_MAX_AGE_SECS);
        }
    }

    private void clearViewCache(long j) {
        String str = TAG;
        Log.d(str, "Executing clearViewCache - maxAgeSecs: " + j);
        HostView hostView = this.mTabletView;
        if (hostView != null) {
            hostView.clearViewCache(j);
        }
        ContentHostView contentHostView = this.mTabletContentView;
        if (contentHostView != null) {
            contentHostView.clearViewCache(j);
        }
    }

    private void logMemoryMeasurements() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.util.$$Lambda$MemoryManager$vQcM6SPj_EpU15NB59b6TDYkWB4 */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MemoryManager.this.lambda$logMemoryMeasurements$57$MemoryManager();
            }
        });
    }

    public /* synthetic */ Object lambda$logMemoryMeasurements$57$MemoryManager() throws Exception {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : memoryInfo.getMemoryStats().entrySet()) {
            hashMap.put(entry.getKey().replaceAll("[.-]", "_"), entry.getValue());
        }
        hashMap.putAll(this.mPanelApp.getPerfLoggingExtraFields());
        this.mPanelApp.rawLogEvent(LOG_EVENT_MEMORY_MEASUREMENT, hashMap);
        return null;
    }

    public void onTrimMemory(int i) {
        clearViewCache((i == 5 || i == 40) ? VIEW_CACHE_PERIODIC_CLEAR_MAX_AGE_SECS : 0);
    }
}
