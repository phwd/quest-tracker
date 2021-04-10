package com.oculus.security.basecomponent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;

@SuppressLint({"EndpointWithoutSwitchOff"})
public class OculusPublicActivity extends Activity {
    @Inject
    @Eager
    private OculusIntentLogger mOculusIntentLogger;

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
    }

    private static final void _UL_injectMe(Context context, OculusPublicActivity oculusPublicActivity) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusPublicActivity);
        } else {
            FbInjector.injectMe(OculusPublicActivity.class, oculusPublicActivity, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusPublicActivity oculusPublicActivity) {
        oculusPublicActivity.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        _UL_injectMe(this, this);
        this.mOculusIntentLogger.logIntent(StringFormatUtil.formatStrLocaleSafe("%s/%s", getPackageName(), getClass().getName()), getIntent());
        doCreate(bundle);
    }
}
