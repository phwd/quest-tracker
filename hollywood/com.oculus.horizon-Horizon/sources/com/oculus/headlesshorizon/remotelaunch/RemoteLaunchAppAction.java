package com.oculus.headlesshorizon.remotelaunch;

import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0b8;
import X.AnonymousClass0b9;
import X.C02790bO;
import X.C02800bY;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.oculus.appmanager.verifier.TrustedAppVerifier;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.horizon.notifications.legacy.contract.NotificationsContract;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.remotelaunchlogger.RemoteLaunchLogger;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import javax.annotation.Nullable;

public class RemoteLaunchAppAction implements AnonymousClass0b8 {
    public static final String KEY_APP_ID = "app_id";
    public static final String KEY_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String KEY_ERROR_STRING = "error_string";
    public static final String KEY_LAUNCH_PARAMS_JSON = "launch_params_json";
    public static final String KEY_RESULT_RECEIVER = "result_receiver";
    public static final String LOGGING_SOURCE = "twilight";
    public static final String TAG = "RemoteLaunchAppAction";
    public AnonymousClass0QC _UL_mInjectionContext;

    private void A00(@Nullable ResultReceiver resultReceiver, String str, @Nullable Exception exc) {
        String str2;
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            if (exc != null) {
                str2 = AnonymousClass006.A07(str, " ", exc.getMessage());
            } else {
                str2 = str;
            }
            bundle.putString(KEY_ERROR_STRING, str2);
            resultReceiver.send(0, bundle);
        }
        if (exc != null) {
            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A97(TAG, str, exc);
        } else {
            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, str);
        }
    }

    @Override // X.AnonymousClass0b8
    public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r14) {
        String str;
        StringBuilder sb;
        String str2;
        this._UL_mInjectionContext = new AnonymousClass0QC(5, AnonymousClass0J2.get(context));
        RemoteLaunchLogger.Builder builder = new RemoteLaunchLogger.Builder();
        builder.source = "twilight";
        builder.deviceSerial = Build.SERIAL;
        String str3 = null;
        if (intent == null || intent.getExtras() == null) {
            builder.errorReason = RemoteLaunchLogger.ERROR_REASON_TWILIGHT_LAUNCH_INTENT_ERROR;
            builder.errorMessage = "Malformed intent";
            ((RemoteLaunchLogger) AnonymousClass0J2.A03(4, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
            A00(null, "Malformed intent", null);
            return;
        }
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
        C02790bO A00 = C02800bY.A00(context, intent);
        if (A00 != null) {
            str = C02790bO.A00(A00);
        } else {
            str = null;
        }
        if (!TrustedAppVerifier.A00((TrustedAppVerifier) AnonymousClass0J2.A03(3, 501, this._UL_mInjectionContext), str, "com.oculus.companion.server")) {
            sb = new StringBuilder("Not called by companion server. Package name: ");
        } else {
            str = intent.getStringExtra("app_id");
            builder.appId = str;
            if (str != null) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    App A02 = ((OVRLibrary) AnonymousClass0J2.A03(1, 569, this._UL_mInjectionContext)).A02(str);
                    if (A02 != null) {
                        if (intent.hasExtra(KEY_LAUNCH_PARAMS_JSON)) {
                            str3 = intent.getStringExtra(KEY_LAUNCH_PARAMS_JSON);
                            builder.deeplinkMessage = str3;
                        }
                        Intent A01 = ((DeeplinkIntentUtils) AnonymousClass0J2.A03(2, 66, this._UL_mInjectionContext)).A01(A02.packageName, str3);
                        if (A01 != null) {
                            try {
                                ((DeeplinkIntentUtils) AnonymousClass0J2.A03(2, 66, this._UL_mInjectionContext)).A03(A01, context);
                                RemoteLaunchLogger.A00((RemoteLaunchLogger) AnonymousClass0J2.A03(4, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext), RemoteLaunchLogger.EVENT_LAUNCH_SUCCESS, builder).A5L();
                                if (resultReceiver != null) {
                                    resultReceiver.send(-1, new Bundle());
                                    return;
                                }
                                return;
                            } catch (SecurityException e) {
                                builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                                builder.errorMessage = "App launch failed";
                                ((RemoteLaunchLogger) AnonymousClass0J2.A03(4, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                                A00(resultReceiver, "App launch failed", e);
                                return;
                            }
                        } else {
                            str2 = "Could not get launch intent";
                            builder.errorReason = RemoteLaunchLogger.ERROR_REASON_LAUNCH_FAILED;
                            builder.errorMessage = str2;
                            ((RemoteLaunchLogger) AnonymousClass0J2.A03(4, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
                            A00(resultReceiver, str2, null);
                        }
                    }
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
            sb = new StringBuilder("Could not fetch app info from library by app id: %s");
            if (str == null) {
                str = "null";
            }
        }
        sb.append(str);
        str2 = sb.toString();
        builder.errorReason = RemoteLaunchLogger.ERROR_REASON_TWILIGHT_LAUNCH_INTENT_ERROR;
        builder.errorMessage = str2;
        ((RemoteLaunchLogger) AnonymousClass0J2.A03(4, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A01(builder);
        A00(resultReceiver, str2, null);
    }
}
