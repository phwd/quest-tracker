package com.oculus.security.basecomponent;

import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
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

    public abstract void doReceive(Context context, Intent intent);

    public static final void _UL_injectMe(Context context, BroadcastReceiverWithIntentLogging broadcastReceiverWithIntentLogging) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), broadcastReceiverWithIntentLogging);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r0, BroadcastReceiverWithIntentLogging broadcastReceiverWithIntentLogging) {
        broadcastReceiverWithIntentLogging.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r0);
    }

    public final void onReceive(Context context, Intent intent) {
        _UL_injectMe(context, this);
        this.mOculusIntentLogger.logIntent(StringFormatUtil.formatStrLocaleSafe("%s/%s", context.getPackageName(), getClass().getName()), intent);
        throw new NullPointerException("doReceive");
    }
}
