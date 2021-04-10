package org.pytorch;

import X.C0616d1;
import X.C0974pq;
import X.EnumC0613cx;
import X.KV;
import android.content.res.AssetManager;

public final class PyTorchAndroid {
    public static native void nativeSetNumThreads(int i);

    public static void setNumThreads(int i) {
        if (i >= 1) {
            nativeSetNumThreads(i);
            return;
        }
        throw new IllegalArgumentException("Number of threads cannot be less than 1");
    }

    static {
        if (!KV.A02()) {
            KV.A00(new C0974pq());
        }
        KV.A01("pytorch_jni");
        try {
            KV.A01("torch-code-gen");
        } catch (Throwable unused) {
        }
    }

    public static C0616d1 loadModuleFromAsset(AssetManager assetManager, String str) {
        return new C0616d1(new NativePeer(str, assetManager, EnumC0613cx.CPU));
    }

    public static C0616d1 loadModuleFromAsset(AssetManager assetManager, String str, EnumC0613cx cxVar) {
        return new C0616d1(new NativePeer(str, assetManager, cxVar));
    }
}
