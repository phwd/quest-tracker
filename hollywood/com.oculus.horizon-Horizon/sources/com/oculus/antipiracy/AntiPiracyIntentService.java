package com.oculus.antipiracy;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.device.DeviceType;

public class AntiPiracyIntentService extends IntentService {
    public static final String ACTION_GET_BACKEND_DEVELOPER_STATUS = "get_backend_developer_status";
    public static final String EXTRA_RESULT_RECEIVER = "result_receiver";
    public static final String KEY_IS_DEVELOPER_MODE_BLOCKED = "is_developer_mode_blocked";
    public static final int RESULT_FAILURE = 1;
    public static final int RESULT_SUCCESS = 0;
    public static final String TAG = "AntiPiracyIntentService";
    @Inject
    @Eager
    public AntiPiracyManager mAntiPiracyManager;

    public AntiPiracyIntentService() {
        super(TAG);
    }

    public final void onCreate() {
        super.onCreate();
        this.mAntiPiracyManager = AntiPiracyManager.A00(AnonymousClass0J2.get(this));
    }

    public final void onHandleIntent(Intent intent) {
        String str;
        String str2;
        ResultReceiver resultReceiver;
        if (DeviceType.is6DOF() || (resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver")) == null) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                str = TAG;
                str2 = "Null or Empty action received";
            } else if (TextUtils.equals(action, ACTION_GET_BACKEND_DEVELOPER_STATUS)) {
                ResultReceiver resultReceiver2 = (ResultReceiver) intent.getParcelableExtra("result_receiver");
                if (resultReceiver2 == null) {
                    str = TAG;
                    str2 = "Received Null receiver. Aborting";
                } else {
                    OculusDeveloperQueryResponse A02 = this.mAntiPiracyManager.A02();
                    if (A02 == null || A02.mOculusDeveloperStatus == null) {
                        AnonymousClass0NO.A08(TAG, "Failed to fetch developer status from backend");
                        resultReceiver2.send(1, null);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(KEY_IS_DEVELOPER_MODE_BLOCKED, A02.mOculusDeveloperStatus.is_oculus_developer_blocked);
                    resultReceiver2.send(0, bundle);
                    return;
                }
            } else {
                AnonymousClass0NO.A0E(TAG, "Received unrecognized action: %s", action);
                return;
            }
            AnonymousClass0NO.A08(str, str2);
            return;
        }
        AnonymousClass0NO.A09(TAG, "AntiPiracy feature is only supported on Quest");
        resultReceiver.send(1, null);
    }
}
