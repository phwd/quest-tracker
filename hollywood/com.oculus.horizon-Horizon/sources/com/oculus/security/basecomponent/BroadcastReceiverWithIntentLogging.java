package com.oculus.security.basecomponent;

import X.AnonymousClass0J2;
import X.AnonymousClass117;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.GraphRequest;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"BadSuperClassBroadcastReceiver.SecureBroadcastReceiver", "EndpointWithoutSwitchOff"})
public abstract class BroadcastReceiverWithIntentLogging extends BroadcastReceiver {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public final void onReceive(Context context, Intent intent) {
        OculusIntentLogger oculusIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, AnonymousClass0J2.get(context));
        this.mOculusIntentLogger = oculusIntentLogger;
        oculusIntentLogger.A00(StringFormatUtil.formatStrLocaleSafe(GraphRequest.GRAPH_PATH_FORMAT, context.getPackageName(), getClass().getName()), null, null, intent);
        throw null;
    }
}
