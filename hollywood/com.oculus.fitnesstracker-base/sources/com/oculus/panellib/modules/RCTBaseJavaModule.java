package com.oculus.panellib.modules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.json.JSONArray;

public abstract class RCTBaseJavaModule extends BroadcastReceiver implements RCTResolveCaller {
    public long RVRCtxTag = 0;

    private static native void nativeEmitEventImpl(long j, String str);

    private static native void nativeEmitEventWithJsonDataImpl(long j, String str, String str2);

    private static native void nativeInvokeCallbackImpl(long j, int i, String str);

    private static native void nativeInvokeCallbackJSONImpl(long j, int i, Object obj);

    public void onReceive(Context context, Intent intent) {
    }

    protected static void nativeInvokeCallback(long j, int i, String str) {
        if (j != 0) {
            nativeInvokeCallbackImpl(j, i, str);
        } else {
            throw new IllegalArgumentException(String.format("Called nativeInvokeCallback with unitialized native module, callbackID=%d", Integer.valueOf(i)));
        }
    }

    protected static void nativeInvokeCallbackJSON(long j, int i, Object obj) {
        if (j != 0) {
            nativeInvokeCallbackJSONImpl(j, i, obj);
        } else {
            throw new IllegalArgumentException(String.format("Called nativeInvokeCallbackJSON with unitialized native module, callbackID=%d", Integer.valueOf(i)));
        }
    }

    protected static void nativeEmitEvent(long j, String str) {
        if (j != 0) {
            nativeEmitEventImpl(j, str);
        }
    }

    protected static void nativeEmitEventWithJsonData(long j, String str, String str2) {
        if (j != 0) {
            nativeEmitEventWithJsonDataImpl(j, str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsNativeLoaded() {
        return this.RVRCtxTag != 0;
    }

    public void invoke(int i, String str) {
        nativeInvokeCallback(this.RVRCtxTag, i, str);
    }

    @Override // com.oculus.panellib.modules.RCTResolveCaller
    public void invokeJSON(int i, JSONArray jSONArray) {
        nativeInvokeCallbackJSON(this.RVRCtxTag, i, jSONArray);
    }
}
