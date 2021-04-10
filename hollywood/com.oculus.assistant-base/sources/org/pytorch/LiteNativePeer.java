package org.pytorch;

import X.AbstractC0614cy;
import X.C0974pq;
import X.EnumC0613cx;
import X.KV;
import com.facebook.jni.HybridData;

public class LiteNativePeer implements AbstractC0614cy {
    public final HybridData mHybridData;

    public static native HybridData initHybrid(String str, int i);

    @Override // X.AbstractC0614cy
    public native IValue forward(IValue... iValueArr);

    public native IValue runMethod(String str, IValue... iValueArr);

    @Override // X.AbstractC0614cy
    public void resetNative() {
        HybridData hybridData = this.mHybridData;
        synchronized (hybridData) {
            hybridData.mDestructor.destruct();
        }
    }

    static {
        if (!KV.A02()) {
            KV.A00(new C0974pq());
        }
        KV.A01("pytorch_jni_lite");
        try {
            KV.A01("torch-code-gen");
        } catch (Throwable unused) {
        }
    }

    public LiteNativePeer(String str, EnumC0613cx cxVar) {
        this.mHybridData = initHybrid(str, cxVar.jniCode);
    }
}
