package com.facebook.common.jniexecutors;

import X.C0139Dd;
import X.C0618dG;
import X.C0829jR;
import X.CN;
import X.CO;
import X.Ca;
import android.util.Log;
import com.facebook.common.time.AwakeTimeSinceBootClock;

public class PooledNativeRunnable extends NativeRunnable {
    public static final CN sPool;

    public PooledNativeRunnable() {
        super(null);
    }

    static {
        CO co = new CO(PooledNativeRunnable.class, AwakeTimeSinceBootClock.INSTANCE);
        C0829jR dGVar = new C0618dG();
        co.A04 = dGVar;
        Ca ca = co.A05;
        if (ca != null) {
            if (dGVar == null) {
                dGVar = new C0829jR(co.A06);
            }
            sPool = new CN(co.A06, co.A02, co.A01, co.A00, co.A03, dGVar, ca);
            return;
        }
        throw new IllegalArgumentException("Must add a clock to the object pool builder");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.common.jniexecutors.PooledNativeRunnable allocate(com.facebook.jni.HybridData r5) {
        /*
            X.CN r3 = com.facebook.common.jniexecutors.PooledNativeRunnable.sPool
            monitor-enter(r3)
            int r0 = r3.A00     // Catch:{ all -> 0x0042 }
            if (r0 <= 0) goto L_0x001f
            int r4 = r0 + -1
            r3.A00 = r4     // Catch:{ all -> 0x0042 }
            java.lang.Object[] r1 = r3.A02     // Catch:{ all -> 0x0042 }
            r2 = r1[r4]     // Catch:{ all -> 0x0042 }
            r0 = 0
            r1[r4] = r0     // Catch:{ all -> 0x0042 }
        L_0x0012:
            X.jR r0 = r3.A07     // Catch:{ all -> 0x0042 }
            boolean r0 = r0 instanceof X.C0618dG     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x003c
            r1 = r2
            com.facebook.common.jniexecutors.NativeRunnable r1 = (com.facebook.common.jniexecutors.NativeRunnable) r1     // Catch:{ all -> 0x0042 }
            r0 = 0
            r1.mNativeExecutor = r0     // Catch:{ all -> 0x0042 }
            goto L_0x003c
        L_0x001f:
            X.jR r1 = r3.A07     // Catch:{ all -> 0x0042 }
            boolean r0 = r1 instanceof X.C0618dG     // Catch:{ all -> 0x0042 }
            if (r0 != 0) goto L_0x0036
            java.lang.String r2 = "Couldn't instantiate object"
            java.lang.Class r0 = r1.A00     // Catch:{ IllegalAccessException | InstantiationException -> 0x002e }
            java.lang.Object r2 = r0.newInstance()     // Catch:{ IllegalAccessException | InstantiationException -> 0x002e }
            goto L_0x0012
        L_0x002e:
            r1 = move-exception
            java.lang.Class<X.CN> r0 = X.CN.class
            X.C0139Dd.A06(r0, r2, r1)
            r2 = 0
            goto L_0x0012
        L_0x0036:
            com.facebook.common.jniexecutors.PooledNativeRunnable r2 = new com.facebook.common.jniexecutors.PooledNativeRunnable
            r2.<init>()
            goto L_0x0012
        L_0x003c:
            monitor-exit(r3)
            com.facebook.common.jniexecutors.PooledNativeRunnable r2 = (com.facebook.common.jniexecutors.PooledNativeRunnable) r2
            r2.mHybridData = r5
            return r2
        L_0x0042:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.jniexecutors.PooledNativeRunnable.allocate(com.facebook.jni.HybridData):com.facebook.common.jniexecutors.PooledNativeRunnable");
    }

    @Override // com.facebook.common.jniexecutors.NativeRunnable
    public void run() {
        try {
            super.run();
        } catch (Exception e) {
            Log.e("PooledNativeRunnable", "run crashed", e);
        }
        CN cn = sPool;
        synchronized (cn) {
            long now = cn.A08.now();
            int i = cn.A00;
            int i2 = cn.A03;
            if (i < (i2 << 1)) {
                cn.A01 = now;
            }
            if (now - cn.A01 > cn.A06) {
                C0139Dd.A01(CN.class, "ObjectPool.checkUsage is compacting the pool.");
                int length = cn.A02.length;
                int max = Math.max(length - i2, cn.A05);
                if (max != length) {
                    CN.A00(cn, max);
                }
            }
            if (cn.A07 instanceof C0618dG) {
                this.mHybridData = null;
            }
            int i3 = cn.A00;
            int i4 = cn.A04;
            if (i3 < i4) {
                int i5 = i3 + 1;
                int length2 = cn.A02.length;
                if (i5 > length2) {
                    CN.A00(cn, Math.min(i4, length2 + i2));
                }
                Object[] objArr = cn.A02;
                int i6 = cn.A00;
                cn.A00 = i6 + 1;
                objArr[i6] = this;
            }
        }
    }
}
