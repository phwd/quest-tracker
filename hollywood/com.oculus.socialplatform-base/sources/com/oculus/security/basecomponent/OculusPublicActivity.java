package com.oculus.security.basecomponent;

import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public class OculusPublicActivity extends Activity {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public void doCreate(Bundle bundle) {
    }

    public static final void _UL_injectMe(Context context, OculusPublicActivity oculusPublicActivity) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusPublicActivity);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r0, OculusPublicActivity oculusPublicActivity) {
        oculusPublicActivity.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r0);
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        _UL_injectMe(this, this);
        this.mOculusIntentLogger.logIntent(StringFormatUtil.formatStrLocaleSafe("%s/%s", getPackageName(), getClass().getName()), getIntent());
    }
}
