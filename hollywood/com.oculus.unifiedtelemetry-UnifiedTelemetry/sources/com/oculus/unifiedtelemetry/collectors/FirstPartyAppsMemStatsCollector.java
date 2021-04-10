package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.Mu;
import X.QC;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.util.Log;
import com.facebook.acra.AppComponentStats;
import com.facebook.acra.util.StatFsUtil;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class FirstPartyAppsMemStatsCollector implements ICollectorWithScreenEvents {
    public static final String CGROUP_FILE_PATH = "/dev/memcg/rmem/cgroup.procs";
    public static final String CGROUP_MEM_STATS_EVENT_NAME = "oculus_mobile_cgroup_mem_stats";
    public static final String DALVIK_PRIVATE_DIRTY = "dalvik_private_dirty";
    public static final String DALVIK_PSS = "dalvik_proportional_set_size";
    public static final String DALVIK_SHARED_DIRTY = "dalvik_shared_dirty";
    public static final String GRAPHICS_KB = "graphics_kb";
    public static final String IS_FOREGROUND = "is_foreground";
    public static final String JAVA_HEAP_KB = "java_heap_kb";
    public static final String NATIVE_HEAP_KB = "native_heap_kb";
    public static final String NATIVE_PRIVATE_DIRTY = "native_private_dirty";
    public static final String NATIVE_PSS = "native_proportional_set_size";
    public static final String NATIVE_SHARED_DIRTY = "native_shared_dirty";
    public static final int ONE_KB = 1024;
    public static final String OS_AVAILABLE_MEM_KB = "os_available_mem_kbytes";
    public static final String OS_EVENT_ID = "os_mem_info_event_id";
    public static final String OS_NATIVE_HEAP_FREE_KB = "os_native_heap_free_kbytes";
    public static final String OS_STATS_EVENT_NAME = "oculus_mobile_os_mem_stats";
    public static final String OS_TOTAL_MEM_KB = "os_total_mem_kbytes";
    public static final String OS_TOTAL_NATIVE_HEAP_KB = "os_total_native_heap_kbytes";
    public static final String OTHER_PRIVATE_DIRTY = "other_private_dirty";
    public static final String OTHER_SHARED_DIRTY = "other_shared_dirty";
    public static final String OTHER_SS = "other_set_size";
    public static final int PERCENT_THRESHOLD = 10;
    public static final String PREV_MEM_USAGE_COUNT = "prev_mem_usage_count";
    public static final int REPORT_INTERVAL = 30;
    public static final String SYSTEM_KB = "system_kb";
    public static final String TAG = "AppsMemStatsCollector";
    public static final String TASK_NAME = "task_name";
    public static final String TOTAL_KB = "total_kb";
    public static volatile FirstPartyAppsMemStatsCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_FirstPartyAppsMemStatsCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final ActivityManager mActivityManager;
    public final Map<String, AppMemInfo> mPreviouslyReportedMemInfo = new HashMap();
    public final Random mRandom;
    public int mTickCounter = 0;

    public static class OSMemInfo {
        public long mAvailableMemInKb;
        public int mEventId;
        public long mNativeHeapFreeInKb;
        public long mTotalMemInKb;
        public long mTotalNativeHeapInKb;
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3v() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
    }

    public static class AppMemInfo {
        public boolean mForeground;
        public Debug.MemoryInfo mMaxMemInfo;
        public final Debug.MemoryInfo mMemInfo;
        public int mPrevMemUsageCount = 0;

        public AppMemInfo(Debug.MemoryInfo memoryInfo, boolean z) {
            this.mMemInfo = memoryInfo;
            this.mMaxMemInfo = memoryInfo;
            this.mForeground = z;
        }
    }

    private Event A01(String str) {
        AppMemInfo appMemInfo = this.mPreviouslyReportedMemInfo.get(str);
        if (appMemInfo == null) {
            return A00(null, str, false, 0);
        }
        return A00(appMemInfo.mMaxMemInfo, str, appMemInfo.mForeground, appMemInfo.mPrevMemUsageCount);
    }

    private OSMemInfo A02() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        this.mActivityManager.getMemoryInfo(memoryInfo);
        OSMemInfo oSMemInfo = new OSMemInfo();
        oSMemInfo.mEventId = this.mRandom.nextInt();
        oSMemInfo.mAvailableMemInKb = memoryInfo.availMem / StatFsUtil.IN_KILO_BYTE;
        oSMemInfo.mTotalMemInKb = memoryInfo.totalMem / StatFsUtil.IN_KILO_BYTE;
        oSMemInfo.mNativeHeapFreeInKb = Debug.getNativeHeapFreeSize() / StatFsUtil.IN_KILO_BYTE;
        oSMemInfo.mTotalNativeHeapInKb = Debug.getNativeHeapSize() / StatFsUtil.IN_KILO_BYTE;
        return oSMemInfo;
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3u() {
        this.mTickCounter = 0;
        try {
            ArrayList arrayList = new ArrayList();
            for (String str : this.mPreviouslyReportedMemInfo.keySet()) {
                arrayList.add(A01(str));
            }
            A03(arrayList, A02());
        } catch (Exception e) {
            Mu.A02(TAG, "Exception collecting 1P app mem info", e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005e, code lost:
        if (r9 == null) goto L_0x006c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007e A[LOOP:1: B:34:0x007c->B:35:0x007e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a8 A[LOOP:2: B:37:0x00a2->B:39:0x00a8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01b5  */
    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A44() {
        /*
        // Method dump skipped, instructions count: 461
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.FirstPartyAppsMemStatsCollector.A44():void");
    }

    @Inject
    public FirstPartyAppsMemStatsCollector(AbstractC0247Xu xu) {
        QC qc = new QC(2, xu);
        this._UL_mInjectionContext = qc;
        this.mActivityManager = (ActivityManager) ((Context) AbstractC0096Hu.A03(1, 3, qc)).getSystemService(AppComponentStats.TAG_ACTIVITY);
        this.mRandom = new Random();
    }

    private void A03(List<Event> list, OSMemInfo oSMemInfo) {
        if (!list.isEmpty()) {
            for (Event event : list) {
                event.A02(OS_EVENT_ID, oSMemInfo.mEventId);
                if (Log.isLoggable(TAG, 3)) {
                    event.A01();
                }
                ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
            }
            Event event2 = new Event(OS_STATS_EVENT_NAME);
            event2.A02(OS_EVENT_ID, oSMemInfo.mEventId);
            event2.A03(OS_AVAILABLE_MEM_KB, oSMemInfo.mAvailableMemInKb);
            event2.A03(OS_TOTAL_MEM_KB, oSMemInfo.mTotalMemInKb);
            event2.A03(OS_NATIVE_HEAP_FREE_KB, oSMemInfo.mNativeHeapFreeInKb);
            event2.A03(OS_TOTAL_NATIVE_HEAP_KB, oSMemInfo.mTotalNativeHeapInKb);
            if (Log.isLoggable(TAG, 3)) {
                event2.A01();
            }
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event2.mName, event2.mContent);
        }
    }

    public static Event A00(@Nullable Debug.MemoryInfo memoryInfo, String str, boolean z, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        String str2;
        String str3;
        String str4;
        String str5;
        Event event = new Event(CGROUP_MEM_STATS_EVENT_NAME);
        event.mContent.putBoolean(IS_FOREGROUND, z);
        int i10 = 0;
        if (memoryInfo != null) {
            i2 = memoryInfo.dalvikPss;
        } else {
            i2 = 0;
        }
        event.A02(DALVIK_PSS, i2);
        if (memoryInfo != null) {
            i3 = memoryInfo.dalvikPrivateDirty;
        } else {
            i3 = 0;
        }
        event.A02(DALVIK_PRIVATE_DIRTY, i3);
        if (memoryInfo != null) {
            i4 = memoryInfo.dalvikSharedDirty;
        } else {
            i4 = 0;
        }
        event.A02(DALVIK_SHARED_DIRTY, i4);
        if (memoryInfo != null) {
            i5 = memoryInfo.nativePss;
        } else {
            i5 = 0;
        }
        event.A02(NATIVE_PSS, i5);
        if (memoryInfo != null) {
            i6 = memoryInfo.nativePrivateDirty;
        } else {
            i6 = 0;
        }
        event.A02(NATIVE_PRIVATE_DIRTY, i6);
        if (memoryInfo != null) {
            i7 = memoryInfo.nativeSharedDirty;
        } else {
            i7 = 0;
        }
        event.A02(NATIVE_SHARED_DIRTY, i7);
        if (memoryInfo != null) {
            i8 = memoryInfo.otherPss;
        } else {
            i8 = 0;
        }
        event.A02(OTHER_SS, i8);
        if (memoryInfo != null) {
            i9 = memoryInfo.otherPrivateDirty;
        } else {
            i9 = 0;
        }
        event.A02(OTHER_PRIVATE_DIRTY, i9);
        if (memoryInfo != null) {
            i10 = memoryInfo.otherSharedDirty;
        }
        event.A02(OTHER_SHARED_DIRTY, i10);
        String str6 = "0";
        if (memoryInfo != null) {
            str2 = memoryInfo.getMemoryStat("summary.java-heap");
        } else {
            str2 = str6;
        }
        event.A06(JAVA_HEAP_KB, str2);
        if (memoryInfo != null) {
            str3 = memoryInfo.getMemoryStat("summary.native-heap");
        } else {
            str3 = str6;
        }
        event.A06(NATIVE_HEAP_KB, str3);
        if (memoryInfo != null) {
            str4 = memoryInfo.getMemoryStat("summary.graphics");
        } else {
            str4 = str6;
        }
        event.A06(GRAPHICS_KB, str4);
        if (memoryInfo != null) {
            str5 = memoryInfo.getMemoryStat("summary.system");
        } else {
            str5 = str6;
        }
        event.A06(SYSTEM_KB, str5);
        if (memoryInfo != null) {
            str6 = memoryInfo.getMemoryStat("summary.total-pss");
        }
        event.A06(TOTAL_KB, str6);
        event.A06(TASK_NAME, str);
        event.A02(PREV_MEM_USAGE_COUNT, i);
        return event;
    }
}
