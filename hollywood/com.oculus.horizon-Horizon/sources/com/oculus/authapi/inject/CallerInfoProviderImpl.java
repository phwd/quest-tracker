package com.oculus.authapi.inject;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C02800bY;
import X.C02930bl;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.authapi.OVRAuth;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class CallerInfoProviderImpl implements OVRAuth.CallerInfoProvider {
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
    public final Intent attachCallerInfo(Intent intent) {
        try {
            C02800bY.A02(intent, (Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext), null);
            return intent;
        } catch (C02930bl e) {
            throw new RuntimeException(e);
        }
    }

    @AutoGeneratedAccessMethod
    public static final CallerInfoProviderImpl A00(AbstractC06640p5 r1) {
        return (CallerInfoProviderImpl) AnonymousClass117.A00(231, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final CallerInfoProviderImpl A01(AbstractC06640p5 r1) {
        return new CallerInfoProviderImpl(r1);
    }

    @Inject
    public CallerInfoProviderImpl(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}