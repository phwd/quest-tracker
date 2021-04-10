package com.oculus.panelapp.anytimeui.v2.tablet.settings.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Process;
import android.os.ResultReceiver;

public class CompanionServerHelper {
    private static final String ACTION_PIN_STATUS = "companion.PIN_STATUS";
    private static final String ACTION_PREFIX = "companion.";
    private static final String BUNDLE_RESULT_NAME = "CS_RESULT";
    private static final String COMPANION_SERVER_PACKAGE = "com.oculus.companion.server";
    private static final String COMPANION_SERVER_RESULT_EXTRA = "RESULT_RECEIVER";
    private static final String COMPANION_SERVER_SERVICE = "com.oculus.companion.server.CompanionService";
    private static final String EXTRA_BASE = "";
    private static final String EXTRA_USER_HANDLE = "USER_HANDLE";

    public interface OnIsPinSet {
        void onResult(boolean z);
    }

    public static void isPinSet(Context context, final OnIsPinSet onIsPinSet) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(EXTRA_USER_HANDLE, Process.myUserHandle());
        Intent intent = new Intent();
        intent.setAction(ACTION_PIN_STATUS);
        intent.setClassName("com.oculus.companion.server", COMPANION_SERVER_SERVICE);
        AnonymousClass1 r2 = new ResultReceiver(null) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.util.CompanionServerHelper.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                Byte b = (Byte) bundle.get(CompanionServerHelper.BUNDLE_RESULT_NAME);
                boolean z = false;
                if (b == null) {
                    onIsPinSet.onResult(false);
                    return;
                }
                OnIsPinSet onIsPinSet = onIsPinSet;
                if (b.byteValue() > 0) {
                    z = true;
                }
                onIsPinSet.onResult(z);
            }
        };
        Parcel obtain = Parcel.obtain();
        r2.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        obtain.recycle();
        intent.putExtra(COMPANION_SERVER_RESULT_EXTRA, (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain));
        intent.putExtras(bundle);
        context.startService(intent);
    }
}
