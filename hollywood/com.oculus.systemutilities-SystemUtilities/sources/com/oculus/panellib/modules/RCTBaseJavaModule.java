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

    protected static void nativeInvokeCallback(long ctxTag, int callbackID, String jsonString) {
        if (ctxTag != 0) {
            nativeInvokeCallbackImpl(ctxTag, callbackID, jsonString);
        } else {
            throw new IllegalArgumentException(String.format("Called nativeInvokeCallback with unitialized native module, callbackID=%d", Integer.valueOf(callbackID)));
        }
    }

    protected static void nativeInvokeCallbackJSON(long ctxTag, int callbackID, Object json) {
        if (ctxTag != 0) {
            nativeInvokeCallbackJSONImpl(ctxTag, callbackID, json);
        } else {
            throw new IllegalArgumentException(String.format("Called nativeInvokeCallbackJSON with unitialized native module, callbackID=%d", Integer.valueOf(callbackID)));
        }
    }

    protected static void nativeEmitEvent(long ctxTag, String eventString) {
        if (ctxTag != 0) {
            nativeEmitEventImpl(ctxTag, eventString);
        }
    }

    protected static void nativeEmitEventWithJsonData(long ctxTag, String eventString, String jsonString) {
        if (ctxTag != 0) {
            nativeEmitEventWithJsonDataImpl(ctxTag, eventString, jsonString);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsNativeLoaded() {
        return this.RVRCtxTag != 0;
    }

    public void invoke(int id, String params) {
        nativeInvokeCallback(this.RVRCtxTag, id, params);
    }

    @Override // com.oculus.panellib.modules.RCTResolveCaller
    public void invokeJSON(int id, JSONArray params) {
        nativeInvokeCallbackJSON(this.RVRCtxTag, id, params);
    }

    public void onReceive(Context context, Intent intent) {
    }
}
