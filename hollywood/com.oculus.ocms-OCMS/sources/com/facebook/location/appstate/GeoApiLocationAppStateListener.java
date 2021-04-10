package com.facebook.location.appstate;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.BLog;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GeoApiLocationAppStateListener {
    @Nullable
    private static GeoApiLocationAppStateListener s_instance;
    protected final long BACKGROUND_DETECTION_TOLERANCE_MS = 250;
    protected final String TAG = GeoApiLocationAppStateListener.class.getSimpleName();
    private final Application mApplication;
    private final CopyOnWriteArrayList<WeakReference<GeoApiLocationSensitiveSubscriber>> mBoundSubscribers;
    private final LifecycleCallback mLifecycleCallback = new LifecycleCallback();
    @Nullable
    private ScheduledExecutorService mNonUiThreadScheduler;

    @VisibleForTesting
    GeoApiLocationAppStateListener(Context context) {
        this.mApplication = (Application) context.getApplicationContext();
        this.mBoundSubscribers = new CopyOnWriteArrayList<>();
    }

    public static synchronized GeoApiLocationAppStateListener getInstance(Context context) {
        GeoApiLocationAppStateListener geoApiLocationAppStateListener;
        synchronized (GeoApiLocationAppStateListener.class) {
            if (s_instance == null) {
                s_instance = new GeoApiLocationAppStateListener(context);
            }
            geoApiLocationAppStateListener = s_instance;
        }
        return geoApiLocationAppStateListener;
    }

    public void bind(GeoApiLocationSensitiveSubscriber geoApiLocationSensitiveSubscriber, ScheduledExecutorService scheduledExecutorService) {
        BLog.d(this.TAG, "binding location sensitive subscriber");
        this.mBoundSubscribers.add(new WeakReference<>(geoApiLocationSensitiveSubscriber));
        synchronized (this) {
            this.mNonUiThreadScheduler = scheduledExecutorService;
            if (this.mBoundSubscribers.size() == 1) {
                startAppStateListening();
            }
        }
    }

    public void unbind(GeoApiLocationSensitiveSubscriber geoApiLocationSensitiveSubscriber) {
        BLog.d(this.TAG, "unbinding location sensitive subscriber");
        Iterator<WeakReference<GeoApiLocationSensitiveSubscriber>> it = this.mBoundSubscribers.iterator();
        while (it.hasNext()) {
            WeakReference<GeoApiLocationSensitiveSubscriber> next = it.next();
            GeoApiLocationSensitiveSubscriber geoApiLocationSensitiveSubscriber2 = next.get();
            if (geoApiLocationSensitiveSubscriber2 == geoApiLocationSensitiveSubscriber || geoApiLocationSensitiveSubscriber2 == null) {
                this.mBoundSubscribers.remove(next);
            }
        }
        synchronized (this) {
            if (this.mBoundSubscribers.isEmpty()) {
                stopAppStateListening();
            }
        }
    }

    public boolean isAppInForeground() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo.importance == 100;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void appBackgroundTriggered() {
        BLog.d(this.TAG, "App background triggered");
        Iterator<WeakReference<GeoApiLocationSensitiveSubscriber>> it = this.mBoundSubscribers.iterator();
        while (it.hasNext()) {
            GeoApiLocationSensitiveSubscriber geoApiLocationSensitiveSubscriber = it.next().get();
            if (geoApiLocationSensitiveSubscriber != null) {
                geoApiLocationSensitiveSubscriber.stopSubscriptions();
            }
        }
    }

    private void startAppStateListening() {
        BLog.d(this.TAG, "Listening for app background");
        this.mApplication.registerActivityLifecycleCallbacks(this.mLifecycleCallback);
    }

    private void stopAppStateListening() {
        BLog.d(this.TAG, "Stopped listening for background");
        this.mApplication.unregisterActivityLifecycleCallbacks(this.mLifecycleCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPause() {
        if (!this.mBoundSubscribers.isEmpty()) {
            BLog.d(this.TAG, "onPause acknowledged");
            this.mNonUiThreadScheduler.schedule(new Runnable() {
                /* class com.facebook.location.appstate.GeoApiLocationAppStateListener.AnonymousClass1 */

                public void run() {
                    if (!GeoApiLocationAppStateListener.this.isAppInForeground()) {
                        GeoApiLocationAppStateListener.this.appBackgroundTriggered();
                    }
                }
            }, 250, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: private */
    public class LifecycleCallback implements Application.ActivityLifecycleCallbacks {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        private LifecycleCallback() {
        }

        public void onActivityPaused(Activity activity) {
            GeoApiLocationAppStateListener.this.onPause();
        }
    }
}
