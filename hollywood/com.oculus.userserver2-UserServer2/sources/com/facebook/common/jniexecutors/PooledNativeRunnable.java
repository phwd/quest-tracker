package com.facebook.common.jniexecutors;

import X.C0035Bh;
import X.KW;
import X.KX;
import X.Kt;
import X.Ss;
import android.util.Log;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class PooledNativeRunnable extends NativeRunnable {
    public static final KW<PooledNativeRunnable> sPool;

    public PooledNativeRunnable() {
        super(null);
    }

    static {
        KX kx = new KX(PooledNativeRunnable.class, AwakeTimeSinceBootClock.INSTANCE);
        Ss bh = new C0035Bh();
        kx.A04 = bh;
        Kt kt = kx.A05;
        if (kt != null) {
            if (bh == null) {
                bh = new Ss(kx.A06);
            }
            sPool = new KW<>(kx.A06, kx.A02, kx.A01, kx.A00, kx.A03, bh, kt);
            return;
        }
        throw new IllegalArgumentException("Must add a clock to the object pool builder");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018  */
    @com.facebook.proguard.annotations.DoNotStrip
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.common.jniexecutors.PooledNativeRunnable allocate(com.facebook.jni.HybridData r6) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.jniexecutors.PooledNativeRunnable.allocate(com.facebook.jni.HybridData):com.facebook.common.jniexecutors.PooledNativeRunnable");
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
        KW<PooledNativeRunnable> kw = sPool;
        synchronized (kw) {
            long now = kw.A08.now();
            int i = kw.A00;
            int i2 = kw.A03;
            if (i < (i2 << 1)) {
                kw.A01 = now;
            }
            if (now - kw.A01 > kw.A06) {
                int length = kw.A02.length;
                int max = Math.max(length - i2, kw.A05);
                if (max != length) {
                    KW.A00(kw, max);
                }
            }
            if (kw.A07 instanceof C0035Bh) {
                this.mHybridData = null;
            }
            int i3 = kw.A00;
            int i4 = kw.A04;
            if (i3 < i4) {
                int i5 = i3 + 1;
                int length2 = kw.A02.length;
                if (i5 > length2) {
                    KW.A00(kw, Math.min(i4, length2 + i2));
                }
                int i6 = kw.A00;
                kw.A00 = i6 + 1;
                kw.A02[i6] = this;
            }
        }
    }
}
