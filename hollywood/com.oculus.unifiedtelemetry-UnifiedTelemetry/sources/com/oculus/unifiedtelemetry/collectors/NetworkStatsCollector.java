package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.Mu;
import X.QC;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.os.RemoteException;
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class NetworkStatsCollector implements ICollectorWithScreenEvents {
    public static final String EVENT_NAME_SLEEPING_NETWORK_ACTIVITY = "oculus_mobile_sleeping_network_activity";
    public static final String TAG = "NetworkStatsCollector";
    public static volatile NetworkStatsCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_NetworkStatsCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public boolean mIsSleeping;
    public NetworkStatsSnapshot mLastSnapshot;
    @Nullable
    public final NetworkStatsManager mNetworkStatsManager;

    public static NetworkStatsSnapshot A00(@Nullable NetworkStatsManager networkStatsManager) {
        if (networkStatsManager == null) {
            Mu.A00(TAG, "NetworkStatsManager is null. Cannot query network stats.");
            return new NetworkStatsSnapshot(0, 0, 0);
        }
        try {
            return new NetworkStatsSnapshot(networkStatsManager.querySummaryForDevice(1, null, 0, SystemClock.elapsedRealtime()));
        } catch (RemoteException | NullPointerException e) {
            Mu.A02(TAG, "Cannot query network stats.", e);
            return new NetworkStatsSnapshot(0, 0, 0);
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3u() {
        this.mIsSleeping = true;
        this.mLastSnapshot = A00(this.mNetworkStatsManager);
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3v() {
        if (this.mIsSleeping) {
            NetworkStatsSnapshot A00 = A00(this.mNetworkStatsManager);
            NetworkStatsSnapshot networkStatsSnapshot = this.mLastSnapshot;
            NetworkStatsSnapshot networkStatsSnapshot2 = new NetworkStatsSnapshot(A00.bytesReceived - networkStatsSnapshot.bytesReceived, A00.bytesSent - networkStatsSnapshot.bytesSent, A00.timestamp - networkStatsSnapshot.timestamp);
            Event event = new Event(EVENT_NAME_SLEEPING_NETWORK_ACTIVITY);
            event.A03("start_realtime", this.mLastSnapshot.timestamp);
            event.A03("end_realtime", A00.timestamp);
            event.A03("bytes_received", networkStatsSnapshot2.bytesReceived);
            event.A03("bytes_sent", networkStatsSnapshot2.bytesSent);
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
        }
        this.mIsSleeping = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0022, code lost:
        if (r1 != false) goto L_0x0024;
     */
    @com.facebook.ultralight.Inject
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NetworkStatsCollector(X.AbstractC0247Xu r4) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 2
            X.QC r1 = new X.QC
            r1.<init>(r0, r4)
            r3._UL_mInjectionContext = r1
            r0 = 3
            r2 = 1
            java.lang.Object r1 = X.AbstractC0096Hu.A03(r2, r0, r1)
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r0 = "power"
            java.lang.Object r0 = r1.getSystemService(r0)
            android.os.PowerManager r0 = (android.os.PowerManager) r0
            if (r0 == 0) goto L_0x0024
            boolean r1 = r0.isScreenOn()
            r0 = 1
            if (r1 == 0) goto L_0x0025
        L_0x0024:
            r0 = 0
        L_0x0025:
            r3.mIsSleeping = r0
            r1 = 3
            X.QC r0 = r3._UL_mInjectionContext
            java.lang.Object r1 = X.AbstractC0096Hu.A03(r2, r1, r0)
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r0 = "netstats"
            java.lang.Object r0 = r1.getSystemService(r0)
            android.app.usage.NetworkStatsManager r0 = (android.app.usage.NetworkStatsManager) r0
            r3.mNetworkStatsManager = r0
            com.oculus.unifiedtelemetry.collectors.NetworkStatsCollector$NetworkStatsSnapshot r0 = A00(r0)
            r3.mLastSnapshot = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.NetworkStatsCollector.<init>(X.Xu):void");
    }

    public static class NetworkStatsSnapshot {
        public final long bytesReceived;
        public final long bytesSent;
        public final long timestamp;

        public NetworkStatsSnapshot(long j, long j2, long j3) {
            this.bytesReceived = j;
            this.bytesSent = j2;
            this.timestamp = j3;
        }

        public NetworkStatsSnapshot(NetworkStats.Bucket bucket) {
            this.bytesReceived = bucket.getRxBytes();
            this.bytesSent = bucket.getTxBytes();
            this.timestamp = bucket.getEndTimeStamp();
        }
    }
}
