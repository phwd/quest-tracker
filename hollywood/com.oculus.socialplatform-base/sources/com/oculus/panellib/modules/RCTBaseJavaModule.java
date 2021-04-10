package com.oculus.panellib.modules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.json.JSONArray;

public abstract class RCTBaseJavaModule extends BroadcastReceiver implements RCTResolveCaller {
    public long RVRCtxTag = 0;

    public static native void nativeEmitEventImpl(long j, String str);

    public static native void nativeEmitEventWithJsonDataImpl(long j, String str, String str2);

    public static native void nativeInvokeCallbackImpl(long j, int i, String str);

    public static native void nativeInvokeCallbackJSONImpl(long j, int i, Object obj);

    public void onReceive(Context context, Intent intent) {
    }

    public static void nativeEmitEvent(long j, String str) {
        if (j != 0) {
            nativeEmitEventImpl(j, str);
        }
    }

    public static void nativeEmitEventWithJsonData(long j, String str, String str2) {
        if (j != 0) {
            nativeEmitEventWithJsonDataImpl(j, str, str2);
        }
    }

    public static void nativeInvokeCallback(long j, int i, String str) {
        if (j != 0) {
            nativeInvokeCallbackImpl(j, i, str);
            return;
        }
        throw new IllegalArgumentException(String.format("Called nativeInvokeCallback with unitialized native module, callbackID=%d", Integer.valueOf(i)));
    }

    public static void nativeInvokeCallbackJSON(long j, int i, Object obj) {
        if (j != 0) {
            nativeInvokeCallbackJSONImpl(j, i, obj);
            return;
        }
        throw new IllegalArgumentException(String.format("Called nativeInvokeCallbackJSON with unitialized native module, callbackID=%d", Integer.valueOf(i)));
    }

    public boolean IsNativeLoaded() {
        if (this.RVRCtxTag != 0) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panellib.modules.RCTResolveCaller
    public void invoke(int i, String str) {
        nativeInvokeCallback(this.RVRCtxTag, i, str);
    }

    @Override // com.oculus.panellib.modules.RCTResolveCaller
    public void invokeJSON(int i, JSONArray jSONArray) {
        nativeInvokeCallbackJSON(this.RVRCtxTag, i, jSONArray);
    }
}
