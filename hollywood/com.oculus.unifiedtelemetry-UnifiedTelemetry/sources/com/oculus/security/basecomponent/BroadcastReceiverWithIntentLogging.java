package com.oculus.security.basecomponent;

import X.AbstractC0096Hu;
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
        OculusIntentLogger A00 = OculusIntentLogger.A00(AbstractC0096Hu.get(context));
        this.mOculusIntentLogger = A00;
        A00.A02(StringFormatUtil.formatStrLocaleSafe("%s/%s", context.getPackageName(), getClass().getName()), null, null, intent);
        throw null;
    }
}
