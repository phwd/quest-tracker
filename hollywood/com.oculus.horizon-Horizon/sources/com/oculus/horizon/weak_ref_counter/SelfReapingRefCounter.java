package com.oculus.horizon.weak_ref_counter;

import com.oculus.util.WeakRef;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.annotation.Nullable;

public abstract class SelfReapingRefCounter<T> {
    public static final int REAPER_INTERVAL_MS = 1000;
    public static final String TAG = "SelfReapingRefCounter";
    public final List<WeakRef<T>> mClients = new ArrayList();
    public final Object mClientsLock = new Object();
    public boolean mInitialized = false;
    public final Object mInitializedLock = new Object();
    @Nullable
    public Timer mReaper = null;
    public final Object mReaperLock = new Object();

    public interface DoSomething<U> {
        void A2I(U u);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        r1 = r5.mReaperLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0 = r5.mReaper;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0042, code lost:
        if (r0 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0044, code lost:
        r0.cancel();
        r5.mReaper = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004a, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A00(com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter r5) {
        /*
            java.lang.Object r4 = r5.mInitializedLock
            monitor-enter(r4)
            boolean r0 = r5.mInitialized     // Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r4)     // Catch:{ all -> 0x004f }
            return
        L_0x0009:
            r0 = 0
            r5.mInitialized = r0     // Catch:{ all -> 0x004f }
            r3 = r5
            com.oculus.horizon.vr_lifecycle.VRLifecycleManager r3 = (com.oculus.horizon.vr_lifecycle.VRLifecycleManager) r3     // Catch:{ all -> 0x004f }
            com.oculus.horizon.vr_lifecycle.MountStatusBase r2 = r3.mMountStatuser     // Catch:{ all -> 0x004f }
            boolean r0 = r2 instanceof com.oculus.horizon.vr_lifecycle.MountStatusPoller     // Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0030
            com.oculus.horizon.vr_lifecycle.MountStatusBroadcastReceiver r2 = (com.oculus.horizon.vr_lifecycle.MountStatusBroadcastReceiver) r2     // Catch:{ all -> 0x004f }
            android.content.Context r1 = r2.mContext     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x0022
            com.oculus.security.basecomponent.OculusPublicBroadcastReceiver r0 = r2.mBroadcastReceiver     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x0022
            r0.unregisterReceiver(r1)     // Catch:{ all -> 0x004f }
        L_0x0022:
            r0 = 0
            r2.mBroadcastReceiver = r0     // Catch:{ all -> 0x004f }
            r2.mContext = r0     // Catch:{ all -> 0x004f }
        L_0x0027:
            com.oculus.security.basecomponent.OculusPublicBroadcastReceiver r1 = r3.mSessionEndReceiver     // Catch:{ all -> 0x004f }
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x004f }
            r1.unregisterReceiver(r0)     // Catch:{ all -> 0x004f }
            monitor-exit(r4)     // Catch:{ all -> 0x004f }
            goto L_0x003d
        L_0x0030:
            com.oculus.horizon.vr_lifecycle.MountStatusPoller r2 = (com.oculus.horizon.vr_lifecycle.MountStatusPoller) r2     // Catch:{ all -> 0x004f }
            java.util.Timer r0 = r2.mMountStatusPoller     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x0027
            r0.cancel()     // Catch:{ all -> 0x004f }
            r0 = 0
            r2.mMountStatusPoller = r0     // Catch:{ all -> 0x004f }
            goto L_0x0027
        L_0x003d:
            java.lang.Object r1 = r5.mReaperLock
            monitor-enter(r1)
            java.util.Timer r0 = r5.mReaper     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x004a
            r0.cancel()     // Catch:{ all -> 0x004c }
            r0 = 0
            r5.mReaper = r0     // Catch:{ all -> 0x004c }
        L_0x004a:
            monitor-exit(r1)     // Catch:{ all -> 0x004c }
            return
        L_0x004c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004c }
            throw r0
        L_0x004f:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter.A00(com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter):void");
    }

    /* JADX WARN: Incorrect args count in method signature: (TT;)Z */
    public static final boolean A01(SelfReapingRefCounter selfReapingRefCounter, Object obj) {
        synchronized (selfReapingRefCounter.mClientsLock) {
            for (int i = 0; i < selfReapingRefCounter.mClients.size(); i++) {
                WeakRef<T> weakRef = selfReapingRefCounter.mClients.get(i);
                if (weakRef != null && weakRef.get() == obj) {
                    return true;
                }
            }
            return false;
        }
    }

    public final void A02(DoSomething<T> doSomething) {
        synchronized (this.mClientsLock) {
            for (WeakRef<T> weakRef : this.mClients) {
                T t = weakRef.get();
                if (t != null) {
                    doSomething.A2I(t);
                }
            }
        }
    }
}
