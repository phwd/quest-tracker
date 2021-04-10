package com.oculus.panelapp.androiddialog;

import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.telemetry.LoggingApi;
import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;

public final class DialogAnalytics {
    private static final String AUTO_CLOSE_PARAM = "auto_close";
    private static final String DIALOG_ACTION_EVENT = "oculus_systemux_dialog_action";
    private static final String DIALOG_ACTION_PARAM = "action";
    private static final String DIALOG_HOST_PARAM = "host";
    private static final String DIALOG_ID_PARAM = "dialog_id";
    private static final String DIALOG_SHOWN_EVENT = "oculus_systemux_dialog_shown";
    private static final String HOST_DELTA_TIME_PARAM = "delta_time";
    private static final String HOST_PACKAGE_PARAM = "host_package";
    private static final String HOST_SERVICE_PARAM = "host_service";
    private static final String TAG = LoggingUtil.tag(DialogAnalytics.class);
    private final LongSupplier mGetElapsedRealTime;
    private final LoggingApi mLoggingApi;

    public DialogAnalytics(LoggingApi loggingApi, LongSupplier longSupplier) {
        this.mLoggingApi = loggingApi;
        this.mGetElapsedRealTime = longSupplier;
    }

    public void logDialogShown(String str, String str2, String str3, String str4) {
        this.mLoggingApi.rawLogEvent(DIALOG_SHOWN_EVENT, "dialog_id", str, DIALOG_HOST_PARAM, str2, HOST_PACKAGE_PARAM, str3, HOST_SERVICE_PARAM, str4);
    }

    public void logDialogAction(String str, String str2, String str3, String str4, String str5, long j) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(this.mGetElapsedRealTime.getAsLong() - j);
        this.mLoggingApi.rawLogEvent(DIALOG_ACTION_EVENT, "dialog_id", str, DIALOG_HOST_PARAM, str2, "action", str3, AUTO_CLOSE_PARAM, "0", HOST_DELTA_TIME_PARAM, String.valueOf(seconds), HOST_PACKAGE_PARAM, str4, HOST_SERVICE_PARAM, str5);
    }
}
