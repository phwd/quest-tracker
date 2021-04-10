package com.oculus.platform.receiver;

import X.AnonymousClass006;
import android.os.Bundle;
import android.os.Handler;
import com.oculus.platform.callback.OVRStringCallback;
import com.oculus.platform.receiver.OVRPlatformResultReceiver;
import com.oculus.platform.util.OVRError;

public class OVRPlatformResultListener implements OVRPlatformResultReceiver.Listener {
    public static final String TAG = "OVRPlatformResultListener";
    public final String mBundleKey;
    public final OVRStringCallback mCallback;
    public final Handler mResultsHandler;

    @Override // com.oculus.platform.receiver.OVRPlatformResultReceiver.Listener
    public void onReceiveResult(int i, Bundle bundle) {
        final String str;
        String str2 = this.mBundleKey;
        if (str2.isEmpty() || bundle.containsKey(str2)) {
            String str3 = this.mBundleKey;
            if (str3.isEmpty() || (str = bundle.getString(str3)) == null) {
                str = "";
            }
            if (i == -1) {
                this.mResultsHandler.post(new Runnable() {
                    /* class com.oculus.platform.receiver.OVRPlatformResultListener.AnonymousClass2 */

                    public void run() {
                        OVRPlatformResultListener.this.mCallback.onSuccess(str);
                    }
                });
            } else {
                this.mResultsHandler.post(new Runnable() {
                    /* class com.oculus.platform.receiver.OVRPlatformResultListener.AnonymousClass3 */

                    public void run() {
                        OVRStringCallback oVRStringCallback = OVRPlatformResultListener.this.mCallback;
                        int i = OVRError.UNKNOWN_ERROR.mCode;
                        String str = str;
                        if (str == null) {
                            str = "Unknown error getting current result";
                        }
                        oVRStringCallback.onClientError(i, str);
                    }
                });
            }
        } else {
            this.mResultsHandler.post(new Runnable() {
                /* class com.oculus.platform.receiver.OVRPlatformResultListener.AnonymousClass1 */

                public void run() {
                    OVRPlatformResultListener oVRPlatformResultListener = OVRPlatformResultListener.this;
                    oVRPlatformResultListener.mCallback.onClientError(OVRError.UNKNOWN_ERROR.mCode, AnonymousClass006.A05("Unable to get data from bundle with key ", oVRPlatformResultListener.mBundleKey));
                }
            });
        }
    }

    public OVRPlatformResultListener(OVRStringCallback oVRStringCallback, Handler handler, String str) {
        this.mResultsHandler = handler;
        this.mCallback = oVRStringCallback;
        this.mBundleKey = str;
    }
}
