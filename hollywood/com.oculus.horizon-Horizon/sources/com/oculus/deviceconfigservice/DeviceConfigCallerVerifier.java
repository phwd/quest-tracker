package com.oculus.deviceconfigservice;

import X.AbstractC06640p5;
import X.AbstractC07090r4;
import X.AnonymousClass0p1;
import X.AnonymousClass1Ch;
import X.C003008y;
import android.content.Context;
import android.content.pm.Signature;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.HashMultimap;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class DeviceConfigCallerVerifier {
    public static final String TAG = "DeviceConfigCallerVerifier";
    public static final AbstractC07090r4<Signature, String> TRUSTED_APPS_OCULUS_FIRST_PARTY;
    @Inject
    @ForAppContext
    public final AnonymousClass0p1<Context> mContextLazy;
    public final AnonymousClass1Ch mTrustedCallerVerifier;

    static {
        HashMultimap hashMultimap = new HashMultimap();
        TRUSTED_APPS_OCULUS_FIRST_PARTY = hashMultimap;
        DeviceConfigVerifiedApps[] values = DeviceConfigVerifiedApps.values();
        for (DeviceConfigVerifiedApps deviceConfigVerifiedApps : values) {
            hashMultimap.A7h(deviceConfigVerifiedApps.getReleaseSignature(), deviceConfigVerifiedApps.getPackageName());
        }
    }

    @Inject
    public DeviceConfigCallerVerifier(AbstractC06640p5 r4) {
        C003008y r0 = new C003008y(294, r4);
        this.mContextLazy = r0;
        this.mTrustedCallerVerifier = new AnonymousClass1Ch(TRUSTED_APPS_OCULUS_FIRST_PARTY, ((Context) r0.get()).getPackageManager());
    }
}
