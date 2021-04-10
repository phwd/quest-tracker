package com.oculus.security.basecomponent;

import X.AnonymousClass0J2;
import X.AnonymousClass117;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.facebook.GraphRequest;
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
        OculusIntentLogger oculusIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, AnonymousClass0J2.get(this));
        this.mOculusIntentLogger = oculusIntentLogger;
        oculusIntentLogger.A00(StringFormatUtil.formatStrLocaleSafe(GraphRequest.GRAPH_PATH_FORMAT, getPackageName(), getClass().getName()), null, null, getIntent());
    }
}
