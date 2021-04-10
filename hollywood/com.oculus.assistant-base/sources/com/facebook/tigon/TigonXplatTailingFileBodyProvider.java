package com.facebook.tigon;

import X.MG;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.jni.HybridData;

public class TigonXplatTailingFileBodyProvider extends TigonBodyProvider {
    public static native HybridData initHybrid(String str, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, int i, int i2, int i3, int i4, boolean z);

    public native void close();

    public native void flush();

    @Override // com.facebook.tigon.TigonBodyProvider
    public void beginStream(TigonBodyStream tigonBodyStream) {
        throw new IllegalStateException("should not be used");
    }

    static {
        MG.A00();
    }
}
