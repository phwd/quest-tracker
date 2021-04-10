package com.oculus.security.basecomponent;

import X.BZ;
import X.IX;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public class OculusPublicActivity extends Activity {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        OculusIntentLogger oculusIntentLogger = (OculusIntentLogger) IX.A00(36, BZ.get(this));
        this.mOculusIntentLogger = oculusIntentLogger;
        oculusIntentLogger.A00(StringFormatUtil.formatStrLocaleSafe("%s/%s", getPackageName(), getClass().getName()), null, null, getIntent());
    }
}
