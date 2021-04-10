package libraries.marauder.analytics.utils.lifecycle;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import libraries.marauder.analytics.utils.concurrent.ThreadUtil;
import libraries.marauder.analytics.utils.lifecycle.ActivityListenerManager;

public class DelayedBackgroundDetector implements ActivityListener {
    public static final long DELAY_AFTER_PAUSE_MS = 5000;
    public List<BackgroundDetectorListener> listeners;
    public Handler mHandler;
    public boolean mIsActivityBackgrounded;
    public boolean mIsAppBackgrounded;
    public boolean mIsDelayedCheckQueued;
    public ThreadUtil mThreadUtil;

    public static class BackgroundDetectorHolder {
        public static final DelayedBackgroundDetector INSTANCE = new DelayedBackgroundDetector(ThreadUtil.ThreadUtilHolder.INSTANCE);
    }

    @Override // libraries.marauder.analytics.utils.lifecycle.ActivityListener
    public void onActivityCreate(Activity activity) {
    }

    @Override // libraries.marauder.analytics.utils.lifecycle.ActivityListener
    public void onActivityDestroy(Activity activity) {
    }

    public static DelayedBackgroundDetector getInstance() {
        return BackgroundDetectorHolder.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyBackgrounded() {
        for (BackgroundDetectorListener backgroundDetectorListener : this.listeners) {
            backgroundDetectorListener.onAppBackgrounded();
        }
    }

    private void notifyForegrounded() {
        for (BackgroundDetectorListener backgroundDetectorListener : this.listeners) {
            backgroundDetectorListener.onAppForegrounded();
        }
    }

    public void addListener(BackgroundDetectorListener backgroundDetectorListener) {
        if (!this.listeners.contains(backgroundDetectorListener)) {
            this.listeners.add(backgroundDetectorListener);
        }
    }

    public boolean isAppBackgrounded() {
        return this.mIsAppBackgrounded;
    }

    @Override // libraries.marauder.analytics.utils.lifecycle.ActivityListener
    public void onActivityPause(Activity activity) {
        this.mThreadUtil.assertOnUiThread();
        this.mIsActivityBackgrounded = true;
        if (!this.mIsDelayedCheckQueued) {
            this.mIsDelayedCheckQueued = true;
            this.mHandler.postDelayed(new Runnable() {
                /* class libraries.marauder.analytics.utils.lifecycle.DelayedBackgroundDetector.AnonymousClass1 */

                public void run() {
                    DelayedBackgroundDetector delayedBackgroundDetector = DelayedBackgroundDetector.this;
                    if (!delayedBackgroundDetector.mIsActivityBackgrounded) {
                        delayedBackgroundDetector.mIsAppBackgrounded = false;
                    } else if (!delayedBackgroundDetector.mIsAppBackgrounded) {
                        delayedBackgroundDetector.mIsAppBackgrounded = true;
                        delayedBackgroundDetector.notifyBackgrounded();
                    }
                    DelayedBackgroundDetector.this.mIsDelayedCheckQueued = false;
                }
            }, 5000);
        }
    }

    @Override // libraries.marauder.analytics.utils.lifecycle.ActivityListener
    public void onActivityResume(Activity activity) {
        this.mThreadUtil.assertOnUiThread();
        this.mIsActivityBackgrounded = false;
        if (this.mIsAppBackgrounded) {
            this.mIsAppBackgrounded = false;
            notifyForegrounded();
        }
    }

    public void removeListener(BackgroundDetectorListener backgroundDetectorListener) {
        if (this.listeners.contains(backgroundDetectorListener)) {
            this.listeners.remove(backgroundDetectorListener);
        }
    }

    public DelayedBackgroundDetector(ThreadUtil threadUtil) {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mIsActivityBackgrounded = true;
        this.mIsAppBackgrounded = true;
        this.listeners = new CopyOnWriteArrayList();
        this.mThreadUtil = threadUtil;
        ActivityListenerManager.ActivityListenerManagerHolder.INSTANCE.addListener(this);
    }
}
