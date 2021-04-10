package com.oculus.config.service;

import X.AbstractC06640p5;
import X.AbstractC07090r4;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.AnonymousClass1Ch;
import X.C003008y;
import X.C003108z;
import X.C01020Iw;
import android.content.Context;
import android.content.pm.Signature;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.HashMultimap;
import com.oculus.signature.SignatureHelper;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class GkServiceCallerVerifier {
    public static final AbstractC07090r4<Signature, String> TRUSTED_APPLICATIONS;
    public AnonymousClass1Ch mTrustedCallerVerifier;

    static {
        HashMultimap hashMultimap = new HashMultimap();
        TRUSTED_APPLICATIONS = hashMultimap;
        hashMultimap.A7h(SignatureHelper.GO_PLATFORM_RELEASE_SIGNATURE, "*|all_packages|*");
        AbstractC07090r4<Signature, String> r2 = TRUSTED_APPLICATIONS;
        r2.A7h(SignatureHelper.QUEST_PLATFORM_RELEASE_SIGNATURE, "*|all_packages|*");
        r2.A7h(SignatureHelper.MIRAMAR_PLATFORM_RELEASE_SIGNATURE, "*|all_packages|*");
        r2.A7h(SignatureHelper.OCULUS_TV_PROD_SIG, "com.oculus.tv");
        r2.A7h(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.livingroom");
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_service_GkServiceCallerVerifier_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(385, r2);
    }

    @AutoGeneratedAccessMethod
    public static final GkServiceCallerVerifier _UL__ULSEP_com_oculus_config_service_GkServiceCallerVerifier_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (GkServiceCallerVerifier) AnonymousClass117.A00(385, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_service_GkServiceCallerVerifier_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(385, r2);
    }

    public void enforceTrustedCaller() {
        if (!AnonymousClass1Ch.A00(this.mTrustedCallerVerifier).A03) {
            throw new SecurityException("access denied");
        }
    }

    @Inject
    public GkServiceCallerVerifier(@ForAppContext Context context) {
        this.mTrustedCallerVerifier = new AnonymousClass1Ch(TRUSTED_APPLICATIONS, context.getPackageManager());
    }

    @AutoGeneratedFactoryMethod
    public static final GkServiceCallerVerifier _UL__ULSEP_com_oculus_config_service_GkServiceCallerVerifier_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new GkServiceCallerVerifier(C003108z.A00(r1));
    }
}
