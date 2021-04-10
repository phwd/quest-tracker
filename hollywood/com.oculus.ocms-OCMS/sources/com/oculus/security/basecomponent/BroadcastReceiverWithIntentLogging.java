package com.oculus.security.basecomponent;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;

@SuppressLint({"BadSuperClassBroadcastReceiver.SecureBroadcastReceiver", "EndpointWithoutSwitchOff"})
public abstract class BroadcastReceiverWithIntentLogging extends BroadcastReceiver {
    @Inject
    @Eager
    private OculusIntentLogger mOculusIntentLogger;

    public abstract void doReceive(Context context, Intent intent);

    private static final void _UL_injectMe(Context context, BroadcastReceiverWithIntentLogging broadcastReceiverWithIntentLogging) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), broadcastReceiverWithIntentLogging);
        } else {
            FbInjector.injectMe(BroadcastReceiverWithIntentLogging.class, broadcastReceiverWithIntentLogging, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, BroadcastReceiverWithIntentLogging broadcastReceiverWithIntentLogging) {
        broadcastReceiverWithIntentLogging.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public final void onReceive(Context context, Intent intent) {
        _UL_injectMe(context, this);
        this.mOculusIntentLogger.logIntent(StringFormatUtil.formatStrLocaleSafe("%s/%s", context.getPackageName(), getClass().getName()), intent);
        doReceive(context, intent);
    }
}
