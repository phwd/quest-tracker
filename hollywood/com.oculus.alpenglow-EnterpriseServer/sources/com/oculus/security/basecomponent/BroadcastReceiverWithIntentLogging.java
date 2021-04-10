package com.oculus.security.basecomponent;

import X.AnonymousClass0Lh;
import X.AnonymousClass13m;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"BadSuperClassBroadcastReceiver.SecureBroadcastReceiver", "EndpointWithoutSwitchOff"})
public abstract class BroadcastReceiverWithIntentLogging extends BroadcastReceiver {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public final void onReceive(Context context, Intent intent) {
        OculusIntentLogger oculusIntentLogger = (OculusIntentLogger) AnonymousClass13m.A00(23, AnonymousClass0Lh.get(context));
        this.mOculusIntentLogger = oculusIntentLogger;
        oculusIntentLogger.A00(StringFormatUtil.formatStrLocaleSafe("%s/%s", context.getPackageName(), getClass().getName()), null, null, intent);
        throw null;
    }
}
