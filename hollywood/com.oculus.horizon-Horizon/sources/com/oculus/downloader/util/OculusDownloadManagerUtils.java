package com.oculus.downloader.util;

import X.AnonymousClass006;
import android.content.Context;
import androidx.annotation.VisibleForTesting;

public class OculusDownloadManagerUtils {
    @VisibleForTesting
    public static final String PERMISSION_DOWNLOAD_WITHOUT_NOTIFICATION = "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION";
    public final Context mApplicationContext;

    public static String A00(int i) {
        if (i == 1) {
            return "PAUSED_WAITING_TO_RETRY";
        }
        if (i == 2) {
            return "PAUSED_WAITING_FOR_NETWORK";
        }
        if (i == 3) {
            return "PAUSED_QUEUED_FOR_WIFI";
        }
        if (i == 4) {
            return "PAUSED_UNKNOWN";
        }
        switch (i) {
            case 1000:
                return "ERROR_UNKNOWN";
            case 1001:
                return "ERROR_FILE_ERROR";
            case 1002:
                return "ERROR_UNHANDLED_HTTP_CODE";
            default:
                switch (i) {
                    case 1004:
                        return "ERROR_HTTP_DATA_ERROR";
                    case 1005:
                        return "ERROR_TOO_MANY_REDIRECTS";
                    case 1006:
                        return "ERROR_INSUFFICIENT_SPACE";
                    case 1007:
                        return "ERROR_DEVICE_NOT_FOUND";
                    case 1008:
                        return "ERROR_CANNOT_RESUME";
                    case 1009:
                        return "ERROR_FILE_ALREADY_EXISTS";
                    default:
                        return AnonymousClass006.A01("UNKNOWN ", i);
                }
        }
    }

    public static String A01(int i) {
        if (i == 1) {
            return "PENDING";
        }
        if (i == 2) {
            return "RUNNING";
        }
        if (i == 4) {
            return "PAUSE";
        }
        if (i == 8) {
            return "SUCCESSFUL";
        }
        if (i != 16) {
            return AnonymousClass006.A01("UNKNOWN ", i);
        }
        return "FAILED";
    }

    public OculusDownloadManagerUtils(Context context) {
        this.mApplicationContext = context.getApplicationContext();
    }
}
