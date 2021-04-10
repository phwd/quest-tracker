package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.QC;
import android.content.Context;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class StorageStatsCollector implements ICollectorWithScreenEvents {
    public static final String EVENT_NAME_STORAGE_STATS = "oculus_mobile_storage_stats";
    public static final long NB_MSEC_BETWEEN_STORAGE_STATS_EVENT = TimeUnit.MINUTES.toMillis(2);
    public static final String TAG = "StorageStatsCollector";
    public static volatile StorageStatsCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_StorageStatsCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public String mDataDirPath;
    public long mLastStorageStatsRecordRealtime = (0 - NB_MSEC_BETWEEN_STORAGE_STATS_EVENT);

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3u() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
    }

    @Inject
    public StorageStatsCollector(AbstractC0247Xu xu) {
        QC qc = new QC(2, xu);
        this._UL_mInjectionContext = qc;
        this.mDataDirPath = ((Context) AbstractC0096Hu.A03(1, 3, qc)).getDataDir().getAbsolutePath();
        A00();
    }

    private void A00() {
        if (SystemClock.elapsedRealtime() - this.mLastStorageStatsRecordRealtime >= NB_MSEC_BETWEEN_STORAGE_STATS_EVENT) {
            Event event = new Event(EVENT_NAME_STORAGE_STATS);
            StatFs statFs = new StatFs(this.mDataDirPath);
            event.A03("storage_total_bytes_data", statFs.getTotalBytes());
            event.A03("storage_available_bytes_data", statFs.getAvailableBytes());
            event.A03("storage_free_bytes_data", statFs.getFreeBytes());
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
            this.mLastStorageStatsRecordRealtime = SystemClock.elapsedRealtime();
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3v() {
        A00();
    }
}
