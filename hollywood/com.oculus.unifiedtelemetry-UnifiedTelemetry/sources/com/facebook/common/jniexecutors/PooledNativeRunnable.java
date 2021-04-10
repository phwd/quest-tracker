package com.facebook.common.jniexecutors;

import X.AbstractC0106Kc;
import X.I6;
import X.KG;
import X.KH;
import X.YN;
import android.util.Log;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class PooledNativeRunnable extends NativeRunnable {
    public static final KG<PooledNativeRunnable> sPool;

    public PooledNativeRunnable() {
        super(null);
    }

    static {
        KH kh = new KH(PooledNativeRunnable.class, AwakeTimeSinceBootClock.INSTANCE);
        YN i6 = new I6();
        kh.A04 = i6;
        AbstractC0106Kc kc = kh.A05;
        if (kc != null) {
            if (i6 == null) {
                i6 = new YN(kh.A06);
            }
            sPool = new KG<>(kh.A06, kh.A02, kh.A01, kh.A00, kh.A03, i6, kc);
            return;
        }
        throw new IllegalArgumentException("Must add a clock to the object pool builder");
    }

    @DoNotStrip
    public static PooledNativeRunnable allocate(HybridData hybridData) {
        Object A00;
        KG<PooledNativeRunnable> kg = sPool;
        synchronized (kg) {
            int i = kg.A00;
            if (i > 0) {
                int i2 = i - 1;
                kg.A00 = i2;
                Object[] objArr = kg.A02;
                A00 = objArr[i2];
                objArr[i2] = null;
            } else {
                A00 = kg.A07.A00();
            }
            kg.A07.A01(A00);
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
        KG<PooledNativeRunnable> kg = sPool;
        synchronized (kg) {
            long now = kg.A08.now();
            int i = kg.A00;
            int i2 = kg.A03;
            if (i < (i2 << 1)) {
                kg.A01 = now;
            }
            if (now - kg.A01 > kg.A06) {
                int length = kg.A02.length;
                int max = Math.max(length - i2, kg.A05);
                if (max != length) {
                    KG.A00(kg, max);
                }
            }
            kg.A07.A02(this);
            int i3 = kg.A00;
            int i4 = kg.A04;
            if (i3 < i4) {
                int i5 = i3 + 1;
                int length2 = kg.A02.length;
                if (i5 > length2) {
                    KG.A00(kg, Math.min(i4, length2 + i2));
                }
                int i6 = kg.A00;
                kg.A00 = i6 + 1;
                kg.A02[i6] = this;
            }
        }
    }
}
