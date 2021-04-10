package org.pytorch;

import X.AbstractC0614cy;
import X.EnumC0613cx;
import X.KV;
import com.facebook.jni.HybridData;

public class NativePeer implements AbstractC0614cy {
    public final HybridData mHybridData;

    public static native HybridData initHybrid(String str, int i);

    public static native HybridData initHybridAndroidAsset(String str, Object obj, int i);

    @Override // X.AbstractC0614cy
    public native IValue forward(IValue... iValueArr);

    public native IValue runMethod(String str, IValue... iValueArr);

    static {
        KV.A01("pytorch_jni");
        try {
            KV.A01("torch-code-gen");
        } catch (Throwable unused) {
        }
    }

    @Override // X.AbstractC0614cy
    public final void resetNative() {
        HybridData hybridData = this.mHybridData;
        synchronized (hybridData) {
            hybridData.mDestructor.destruct();
        }
    }

    public NativePeer(String str, Object obj, EnumC0613cx cxVar) {
        this.mHybridData = initHybridAndroidAsset(str, obj, cxVar.jniCode);
    }
}
