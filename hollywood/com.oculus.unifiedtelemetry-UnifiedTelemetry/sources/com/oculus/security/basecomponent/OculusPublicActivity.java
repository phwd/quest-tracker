package com.oculus.security.basecomponent;

import X.AbstractC0096Hu;
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
        OculusIntentLogger A00 = OculusIntentLogger.A00(AbstractC0096Hu.get(this));
        this.mOculusIntentLogger = A00;
        A00.A02(StringFormatUtil.formatStrLocaleSafe("%s/%s", getPackageName(), getClass().getName()), null, null, getIntent());
    }
}
