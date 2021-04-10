package com.facebook.wifiscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import com.facebook.common.futures.ListenableSettableFuture;
import com.facebook.common.time.Clock;
import com.facebook.common.time.MonotonicClock;
import com.facebook.wifiscan.WifiScanOperationException;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class WifiScanOperation extends ListenableSettableFuture<List<WifiScanResult>> {
    private static final Class<?> TAG = WifiScanOperation.class;
    private BroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private boolean mCurrentlyRunning;
    private final WifiScanEligibilityUtil mEligibilityUtil;
    private final ScheduledExecutorService mHandlerExecutorService;
    private WifiScanOperationParams mParams;
    private final MonotonicClock mSinceBootClock;
    private ScheduledFuture mTimeoutFuture;
    private final Clock mWallClock;
    private WifiScan mWifiScan;
    private final WifiScanResultTimestampFix mWifiScanResultTimestampFix;

    public WifiScanOperation(WifiScan wifiScan, Context context, WifiScanEligibilityUtil wifiScanEligibilityUtil, MonotonicClock monotonicClock, Clock clock, WifiScanResultTimestampFix wifiScanResultTimestampFix, ScheduledExecutorService scheduledExecutorService) {
        this.mWifiScan = wifiScan;
        this.mContext = context;
        this.mEligibilityUtil = wifiScanEligibilityUtil;
        this.mWallClock = clock;
        this.mWifiScanResultTimestampFix = wifiScanResultTimestampFix;
        this.mSinceBootClock = monotonicClock;
        this.mHandlerExecutorService = scheduledExecutorService;
    }

    public synchronized void start(WifiScanOperationParams wifiScanOperationParams) {
        start(wifiScanOperationParams, null);
    }

    public synchronized void start(WifiScanOperationParams wifiScanOperationParams, @Nullable String str) {
        try {
            doStart(wifiScanOperationParams, str);
        } catch (WifiScanOperationException e) {
            finishWithThrowable(e);
        }
        return;
    }

    private void doStart(WifiScanOperationParams wifiScanOperationParams, @Nullable String str) throws WifiScanOperationException {
        Preconditions.checkNotNull(wifiScanOperationParams);
        Preconditions.checkState(!this.mCurrentlyRunning, "already running");
        Preconditions.checkState(!isDone(), "already done");
        this.mCurrentlyRunning = true;
        this.mParams = wifiScanOperationParams;
        if (!this.mEligibilityUtil.isWifiAvailable()) {
            throw new WifiScanOperationException(WifiScanOperationException.Type.NOT_SUPPORTED);
        } else if (!this.mEligibilityUtil.areTimestampsSupported()) {
            throw new WifiScanOperationException(WifiScanOperationException.Type.NOT_SUPPORTED);
        } else if (!this.mEligibilityUtil.appHasPermissionToScan()) {
            throw new WifiScanOperationException(WifiScanOperationException.Type.PERMISSION_DENIED);
        } else if (this.mEligibilityUtil.isWifiEnabled() || this.mEligibilityUtil.isScanAlwaysAvailable()) {
            if (!this.mParams.forceActiveScan) {
                List<ScanResult> wifiManagerScanResults = this.mWifiScan.getWifiManagerScanResults(false);
                if (this.mParams.forceTimestampFixWindowMs > 0) {
                    this.mWifiScanResultTimestampFix.fixTimestamps(wifiManagerScanResults, this.mParams.forceTimestampFixWindowMs);
                } else {
                    this.mWifiScanResultTimestampFix.fixTimestamps(wifiManagerScanResults);
                }
                List<ScanResult> resultsMatchingAgeLimit = ScanResultAgeUtil.getResultsMatchingAgeLimit(wifiManagerScanResults, this.mParams.ageLimitMs, this.mSinceBootClock.now());
                if (resultsMatchingAgeLimit != null && !resultsMatchingAgeLimit.isEmpty()) {
                    if (this.mParams.returnAllResults) {
                        finishWithResults(WifiScanResult.fromScanResults(wifiManagerScanResults, this.mWallClock, this.mSinceBootClock));
                        return;
                    } else {
                        finishWithResults(WifiScanResult.fromScanResults(resultsMatchingAgeLimit, this.mWallClock, this.mSinceBootClock));
                        return;
                    }
                }
            }
            if (this.mParams.timeoutMs != 0) {
                scheduleTimeout();
                this.mBroadcastReceiver = new BroadcastReceiver() {
                    /* class com.facebook.wifiscan.WifiScanOperation.AnonymousClass1 */

                    public void onReceive(Context context, Intent intent) {
                        if (!isInitialStickyBroadcast()) {
                            WifiScanOperation.this.mHandlerExecutorService.execute(new Runnable() {
                                /* class com.facebook.wifiscan.WifiScanOperation.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    WifiScanOperation.this.onScanResultsAvailable();
                                }
                            });
                        }
                    }
                };
                this.mContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                if (!this.mWifiScan.triggerActiveWifiScan(str)) {
                    throw new WifiScanOperationException(WifiScanOperationException.Type.UNKNOWN_ERROR);
                }
                return;
            }
            throw new WifiScanOperationException(WifiScanOperationException.Type.TIMEOUT);
        } else {
            throw new WifiScanOperationException(WifiScanOperationException.Type.USER_DISABLED);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void onScanResultsAvailable() {
        if (this.mCurrentlyRunning) {
            List<ScanResult> wifiManagerScanResults = this.mWifiScan.getWifiManagerScanResults(false);
            if (this.mParams.forceTimestampFixWindowMs > 0) {
                this.mWifiScanResultTimestampFix.fixTimestamps(wifiManagerScanResults, this.mParams.forceTimestampFixWindowMs);
            } else {
                this.mWifiScanResultTimestampFix.fixTimestamps(wifiManagerScanResults);
            }
            List resultsMatchingAgeLimit = ScanResultAgeUtil.getResultsMatchingAgeLimit(wifiManagerScanResults, this.mParams.ageLimitMs, this.mSinceBootClock.now());
            if (this.mParams.returnAllResults) {
                if (wifiManagerScanResults == null) {
                    wifiManagerScanResults = new ArrayList<>();
                }
                finishWithResults(WifiScanResult.fromScanResults(wifiManagerScanResults, this.mWallClock, this.mSinceBootClock));
            } else {
                if (resultsMatchingAgeLimit == null) {
                    resultsMatchingAgeLimit = new ArrayList();
                }
                finishWithResults(WifiScanResult.fromScanResults(resultsMatchingAgeLimit, this.mWallClock, this.mSinceBootClock));
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void onTimeout() {
        if (this.mCurrentlyRunning) {
            finishWithThrowable(new WifiScanOperationException(WifiScanOperationException.Type.TIMEOUT));
        }
    }

    private void scheduleTimeout() {
        this.mTimeoutFuture = this.mHandlerExecutorService.schedule(new Runnable() {
            /* class com.facebook.wifiscan.WifiScanOperation.AnonymousClass2 */

            public void run() {
                WifiScanOperation.this.onTimeout();
            }
        }, this.mParams.timeoutMs, TimeUnit.MILLISECONDS);
    }

    private void cancelTimeout() {
        ScheduledFuture scheduledFuture = this.mTimeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.mTimeoutFuture = null;
        }
    }

    private void finishWithResults(List<WifiScanResult> list) {
        cleanup();
        set(list);
    }

    private void finishWithThrowable(Throwable th) {
        cleanup();
        setException(th);
    }

    private void cleanup() {
        this.mCurrentlyRunning = false;
        this.mParams = null;
        BroadcastReceiver broadcastReceiver = this.mBroadcastReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
            this.mBroadcastReceiver = null;
        }
        cancelTimeout();
    }
}
