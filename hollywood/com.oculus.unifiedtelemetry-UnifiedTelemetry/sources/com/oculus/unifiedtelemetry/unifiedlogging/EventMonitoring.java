package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.C0478qP;
import X.HY;
import X.QC;
import X.Rc;
import X.Rd;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.analytics2.RtcClock;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_MobileConfigClient_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_oculus_directboot_ForDeviceProtectedStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_RtcClock_ULSEP_BINDING_ID"})
@SuppressLint({"FieldInjectedContext"})
public class EventMonitoring {
    @VisibleForTesting
    public static final String BEACON_COUNTER = "beacon_counter";
    public static final String CORRUPTED_EVENT_COUNT = "corrupted_event_count";
    public static final String INCOMPLETE_EVENT_COUNT = "incomplete_event_count";
    public static final String SHARED_PREFS_MONITORING = "oculus.unifiedtelemetry.monitoring";
    public static final String TIME_WINDOW_IN_SEC = "time_window_in_sec";
    public static final String TOTAL_EVENT_COUNT = "total_event_count";
    public static final String UT_TELEMETRY_MONITORING = "oculus_ut_telemetry_monitoring";
    public QC _UL_mInjectionContext;
    public final AtomicInteger mCorruptedEventCount;
    @VisibleForTesting
    public ConcurrentHashMap<String, Object> mEventMonitoringMap;
    public final AtomicInteger mIncompleteEventCount;
    public long mMonitoringStartTime;
    public final ReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();
    public final Object mSharedPrefLock = new Object();
    public final SharedPreferences mSharedPreferences;
    public final AtomicInteger mTotalEventCount;
    public final int mUploadThreshold;

    public enum FunnelEventType {
        START,
        ACTION,
        END,
        UNKNOWN
    }

    @VisibleForTesting
    public static class MonitoringData {
        @DoNotStrip
        public AtomicInteger mCount = new AtomicInteger(0);
        public final Set<String> mUsingRestrictedKeys = Collections.newSetFromMap(new ConcurrentHashMap());
    }

    /* JADX INFO: finally extract failed */
    @SuppressLint({"CommitPrefEdits", "SharedPreferencesUse"})
    public static String A00(EventMonitoring eventMonitoring, boolean z) {
        synchronized (eventMonitoring.mSharedPrefLock) {
            int i = eventMonitoring.mSharedPreferences.getInt(BEACON_COUNTER, 1);
            eventMonitoring.mEventMonitoringMap.put(BEACON_COUNTER, Integer.valueOf(i));
            if (z) {
                eventMonitoring.mSharedPreferences.edit().remove(BEACON_COUNTER).commit();
            } else {
                eventMonitoring.mSharedPreferences.edit().putInt(BEACON_COUNTER, i + 1).commit();
            }
        }
        eventMonitoring.mReadWriteLock.writeLock().lock();
        try {
            long A00 = ((RtcClock) AbstractC0096Hu.A03(2, 30, eventMonitoring._UL_mInjectionContext)).A00();
            eventMonitoring.mEventMonitoringMap.put(TOTAL_EVENT_COUNT, Integer.valueOf(eventMonitoring.mTotalEventCount.getAndSet(0)));
            eventMonitoring.mEventMonitoringMap.put(INCOMPLETE_EVENT_COUNT, Integer.valueOf(eventMonitoring.mIncompleteEventCount.getAndSet(0)));
            eventMonitoring.mEventMonitoringMap.put(CORRUPTED_EVENT_COUNT, Integer.valueOf(eventMonitoring.mCorruptedEventCount.getAndSet(0)));
            eventMonitoring.mEventMonitoringMap.put(TIME_WINDOW_IN_SEC, Long.valueOf(A00 - eventMonitoring.mMonitoringStartTime));
            ConcurrentHashMap<String, Object> concurrentHashMap = eventMonitoring.mEventMonitoringMap;
            eventMonitoring.mEventMonitoringMap = new ConcurrentHashMap<>();
            eventMonitoring.mMonitoringStartTime = A00;
            eventMonitoring.mReadWriteLock.writeLock().unlock();
            return new HY().A07(concurrentHashMap);
        } catch (Throwable th) {
            eventMonitoring.mReadWriteLock.writeLock().unlock();
            throw th;
        }
    }

    @Inject
    public EventMonitoring(AbstractC0247Xu xu) {
        long A00;
        this._UL_mInjectionContext = new QC(3, xu);
        MobileConfigClient mobileConfigClient = (MobileConfigClient) AbstractC0096Hu.A03(0, 134, this._UL_mInjectionContext);
        Rd A002 = Rd.A00(new Rd());
        A002.A00 = true;
        Rd A003 = Rd.A00(A002);
        A003.A02 = true;
        if (mobileConfigClient.mIsInitialized) {
            A00 = ((Rc) AbstractC0096Hu.A03(0, 115, mobileConfigClient._UL_mInjectionContext)).A2c(36591746972581888L, A003);
        } else {
            A00 = C0478qP.A00(36591746972581888L);
        }
        this.mUploadThreshold = (int) A00;
        this.mSharedPreferences = ((Context) AbstractC0096Hu.A03(1, 99, this._UL_mInjectionContext)).getSharedPreferences(SHARED_PREFS_MONITORING, 0);
        this.mMonitoringStartTime = ((RtcClock) AbstractC0096Hu.A03(2, 30, this._UL_mInjectionContext)).A00();
        this.mTotalEventCount = new AtomicInteger(0);
        this.mIncompleteEventCount = new AtomicInteger(0);
        this.mCorruptedEventCount = new AtomicInteger(0);
        this.mEventMonitoringMap = new ConcurrentHashMap<>();
    }
}
