package com.oculus.platform.util;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.oculus.platform.callback.OVRErrorOrReleaseCallback;
import com.oculus.platform.callback.OVRStringCallback;

public class Util {
    public static final String TAG = "com.oculus.platform.util.Util";

    public static Bundle generateArgsBundle(String str, int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("sdk_major_version", i);
        bundle.putInt("sdk_minor_version", i2);
        bundle.putString("app_id", str);
        return bundle;
    }

    public static boolean isServiceError(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return bundle.containsKey("error_code");
    }

    public static Bundle makeErrorResultQuiet(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code", i);
        bundle.putString("error_message", str);
        return bundle;
    }

    public static OVRError getServiceError(Bundle bundle) {
        if (isServiceError(bundle)) {
            return OVRError.fromInt(bundle.getInt("error_code", OVRError.UNKNOWN_ERROR.mCode));
        }
        throw new IllegalArgumentException("tried to fetch error code from non-error");
    }

    public static String getServiceErrorMessage(Bundle bundle) {
        if (isServiceError(bundle)) {
            return bundle.getString("error_message");
        }
        throw new IllegalArgumentException("tried to fetch error message from non-error");
    }

    public static void handleClientError(Handler handler, final OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback, final OVRError oVRError) {
        if (oVRErrorOrReleaseCallback != null) {
            handler.post(new Runnable() {
                /* class com.oculus.platform.util.Util.AnonymousClass2 */

                public void run() {
                    OVRErrorOrReleaseCallback oVRErrorOrReleaseCallback = OVRErrorOrReleaseCallback.this;
                    OVRError oVRError = oVRError;
                    oVRErrorOrReleaseCallback.onClientError(oVRError.mCode, oVRError.mErrorMessage);
                }
            });
        }
    }

    public static void handleClientError(Handler handler, final OVRStringCallback oVRStringCallback, final OVRError oVRError) {
        if (oVRStringCallback != null) {
            handler.post(new Runnable() {
                /* class com.oculus.platform.util.Util.AnonymousClass1 */

                public void run() {
                    OVRStringCallback oVRStringCallback = OVRStringCallback.this;
                    OVRError oVRError = oVRError;
                    oVRStringCallback.onClientError(oVRError.mCode, oVRError.mErrorMessage);
                }
            });
        }
    }

    public static Bundle makeErrorResult(OVRError oVRError) {
        return makeErrorResult(null, oVRError.mErrorMessage, oVRError.mCode);
    }

    public static Bundle makeErrorResult(Throwable th, String str, int i) {
        if (th == null) {
            th = new Throwable("stacktrace");
        }
        Log.e(TAG, "operation failed", th);
        return makeErrorResultQuiet(str, i);
    }
}
