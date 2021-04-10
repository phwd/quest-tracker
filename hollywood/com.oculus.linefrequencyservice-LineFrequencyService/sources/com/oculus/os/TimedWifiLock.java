package com.oculus.os;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import java.util.concurrent.TimeUnit;

public class TimedWifiLock {
    private static final String TAG = "TimedWifiLock";
    private static final int WAKELOCK_TIMEOUT_LAG_MILLIS = 300000;
    private final Handler mHandler;
    private boolean mIsHeld;
    private final ReleaseLocksCallback mReleaseLocksCallback;
    private final int mTimeoutMillis;
    private final PowerManager.WakeLock mWakeLock;
    private final WifiManager.WifiLock mWifiLock;

    public TimedWifiLock(Context context, String name, int timeoutMillis, Handler handler) {
        if (timeoutMillis <= 0 || ((long) timeoutMillis) > TimeUnit.HOURS.toMillis(5)) {
            throw new IllegalArgumentException("Invalid timeout.");
        }
        String name2 = "TimedLock:" + name;
        this.mTimeoutMillis = timeoutMillis;
        this.mHandler = handler;
        this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, name2);
        this.mWifiLock = ((WifiManager) context.getSystemService("wifi")).createWifiLock(name2);
        this.mReleaseLocksCallback = new ReleaseLocksCallback();
    }

    public synchronized boolean acquire() {
        if (isHeld()) {
            Log.w(TAG, "Lock " + this.mWakeLock.getTag() + " is already held");
            return false;
        }
        this.mIsHeld = true;
        this.mWakeLock.acquire((long) (this.mTimeoutMillis + WAKELOCK_TIMEOUT_LAG_MILLIS));
        this.mWifiLock.acquire();
        this.mHandler.postDelayed(this.mReleaseLocksCallback, (long) this.mTimeoutMillis);
        return true;
    }

    public synchronized boolean release() {
        this.mHandler.removeCallbacks(this.mReleaseLocksCallback);
        return releaseInternalLocked();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean releaseInternalLocked() {
        if (!isHeld()) {
            Log.w(TAG, "Lock " + this.mWakeLock.getTag() + " is not held");
            return false;
        }
        this.mIsHeld = false;
        if (this.mWifiLock.isHeld()) {
            this.mWifiLock.release();
        }
        if (!this.mWakeLock.isHeld()) {
            return true;
        }
        this.mWakeLock.release();
        return true;
    }

    public synchronized boolean isHeld() {
        return this.mIsHeld;
    }

    private class ReleaseLocksCallback implements Runnable {
        private ReleaseLocksCallback() {
        }

        public void run() {
            synchronized (TimedWifiLock.this) {
                Log.d(TimedWifiLock.TAG, "Releasing lock " + TimedWifiLock.this.mWakeLock.getTag() + " after timeout.");
                TimedWifiLock.this.releaseInternalLocked();
            }
        }
    }
}
