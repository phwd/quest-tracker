package com.oculus.downloader.util;

import android.app.DownloadManager;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.PointerIconCompat;

public class OculusDownloadManagerUtils {
    @VisibleForTesting
    static final String PERMISSION_DOWNLOAD_WITHOUT_NOTIFICATION = "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION";
    private final Context mApplicationContext;

    public OculusDownloadManagerUtils(Context context) {
        this.mApplicationContext = context.getApplicationContext();
    }

    public void setAllowedOverMetered(DownloadManager.Request request, boolean z) {
        request.setAllowedOverMetered(z);
    }

    public void setNotificationVisibility(DownloadManager.Request request, int i) {
        int checkCallingOrSelfPermission = this.mApplicationContext.checkCallingOrSelfPermission(PERMISSION_DOWNLOAD_WITHOUT_NOTIFICATION);
        if (i == 2 && checkCallingOrSelfPermission != 0) {
            i = 0;
        }
        request.setNotificationVisibility(i);
    }

    public static String stringifyStatus(int i) {
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
        if (i == 16) {
            return "FAILED";
        }
        return "UNKNOWN " + i;
    }

    public static String stringifyReason(int i) {
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
            case PointerIconCompat.TYPE_CONTEXT_MENU:
                return "ERROR_FILE_ERROR";
            case 1002:
                return "ERROR_UNHANDLED_HTTP_CODE";
            default:
                switch (i) {
                    case PointerIconCompat.TYPE_WAIT:
                        return "ERROR_HTTP_DATA_ERROR";
                    case 1005:
                        return "ERROR_TOO_MANY_REDIRECTS";
                    case 1006:
                        return "ERROR_INSUFFICIENT_SPACE";
                    case PointerIconCompat.TYPE_CROSSHAIR:
                        return "ERROR_DEVICE_NOT_FOUND";
                    case PointerIconCompat.TYPE_TEXT:
                        return "ERROR_CANNOT_RESUME";
                    case PointerIconCompat.TYPE_VERTICAL_TEXT:
                        return "ERROR_FILE_ALREADY_EXISTS";
                    default:
                        return "UNKNOWN " + i;
                }
        }
    }
}
