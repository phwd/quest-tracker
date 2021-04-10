package com.facebook.common.jniexecutors;

import X.AnonymousClass0Jd;
import X.AnonymousClass0Je;
import X.AnonymousClass0K8;
import X.C01230Vg;
import X.C04980re;
import android.util.Log;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class PooledNativeRunnable extends NativeRunnable {
    public static final AnonymousClass0Jd<PooledNativeRunnable> sPool;

    public PooledNativeRunnable() {
        super(null);
    }

    static {
        AnonymousClass0Je r1 = new AnonymousClass0Je(PooledNativeRunnable.class, AwakeTimeSinceBootClock.INSTANCE);
        C04980re r8 = new C01230Vg();
        r1.A04 = r8;
        AnonymousClass0K8 r9 = r1.A05;
        if (r9 != null) {
            if (r8 == null) {
                r8 = new C04980re(r1.A06);
            }
            sPool = new AnonymousClass0Jd<>(r1.A06, r1.A02, r1.A01, r1.A00, r1.A03, r8, r9);
            return;
        }
        throw new IllegalArgumentException("Must add a clock to the object pool builder");
    }

    @DoNotStrip
    public static PooledNativeRunnable allocate(HybridData hybridData) {
        Object A00;
        AnonymousClass0Jd<PooledNativeRunnable> r4 = sPool;
        synchronized (r4) {
            int i = r4.A00;
            if (i > 0) {
                int i2 = i - 1;
                r4.A00 = i2;
                Object[] objArr = r4.A02;
                A00 = objArr[i2];
                objArr[i2] = null;
            } else {
                A00 = r4.A07.A00();
            }
            r4.A07.A01(A00);
        }
        PooledNativeRunnable pooledNativeRunnable = (PooledNativeRunnable) A00;
        pooledNativeRunnable.mHybridData = hybridData;
        return pooledNativeRunnable;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.common.jniexecutors.NativeRunnable
    public void run() {
        try {
            super.run();
        } catch (Exception e) {
            Log.e("PooledNativeRunnable", "run crashed", e);
        }
        AnonymousClass0Jd<PooledNativeRunnable> r3 = sPool;
        synchronized (r3) {
            long now = r3.A08.now();
            int i = r3.A00;
            int i2 = r3.A03;
            if (i < (i2 << 1)) {
                r3.A01 = now;
            }
            if (now - r3.A01 > r3.A06) {
                int length = r3.A02.length;
                int max = Math.max(length - i2, r3.A05);
                if (max != length) {
                    AnonymousClass0Jd.A00(r3, max);
                }
            }
            r3.A07.A02(this);
            int i3 = r3.A00;
            int i4 = r3.A04;
            if (i3 < i4) {
                int i5 = i3 + 1;
                int length2 = r3.A02.length;
                if (i5 > length2) {
                    AnonymousClass0Jd.A00(r3, Math.min(i4, length2 + i2));
                }
                int i6 = r3.A00;
                r3.A00 = i6 + 1;
                r3.A02[i6] = this;
            }
        }
    }
}
