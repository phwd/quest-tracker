package com.oculus.userserver.managerservice.callerverifiers;

import X.Mi;
import X.N6;
import X.Qy;
import X.R3;
import X.TW;
import android.content.Context;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableListMultimap;
import com.oculus.signature.SignatureHelper;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.util.constants.OculusConstants;
import java.util.ArrayList;
import java.util.List;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class SignatureVerifier {
    public static final InternalSignatureVerifier APP_FIRST_TIME_NUX;
    public static final InternalSignatureVerifier APP_HOME;
    public static final InternalSignatureVerifier APP_HORIZON;
    public static final InternalSignatureVerifier APP_OCMS;
    public static final InternalSignatureVerifier APP_SHELL = A00(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.vrshell");
    public static final InternalSignatureVerifier APP_STORE;
    public static final InternalSignatureVerifier APP_SYSTEMUX = A00(SignatureHelper.OCULUS_CORE_RELEASE_SIGNATURE, "com.oculus.systemux");
    public static final InternalSignatureVerifier APP_SYSTEM_UTILITIES;
    public static final List<InternalSignatureVerifier> allVerifiers = new ArrayList();

    public static class InternalSignatureVerifier {
        public R3 mCallerVerifier;
        public final Qy<Signature, String> trustedPackages;

        public InternalSignatureVerifier(Qy<Signature, String> qy) {
            this.trustedPackages = qy;
        }
    }

    static {
        Signature signature = SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG;
        APP_HOME = A00(signature, "com.oculus.vrshell.home");
        APP_SYSTEM_UTILITIES = A00(signature, OculusConstants.PACKAGE_NAME_SYSTEM_UTILITIES);
        APP_STORE = A00(signature, "com.oculus.store");
        APP_FIRST_TIME_NUX = A00(signature, "com.oculus.firsttimenux");
        Signature signature2 = SignatureHelper.OCULUS_PROD_RELEASE_SIGNATURE;
        APP_HORIZON = A00(signature2, "com.oculus.horizon");
        APP_OCMS = A00(signature2, "com.oculus.ocms");
    }

    @Inject
    public SignatureVerifier(@ForAppContext Context context) {
        for (InternalSignatureVerifier internalSignatureVerifier : allVerifiers) {
            internalSignatureVerifier.mCallerVerifier = new R3(internalSignatureVerifier.trustedPackages, context.getPackageManager());
        }
    }

    public static InternalSignatureVerifier A00(Signature signature, String str) {
        N6 A01 = ImmutableListMultimap.A01();
        A01.A04(signature, str);
        if (!Build.FINGERPRINT.endsWith("/release-keys")) {
            A01.A04(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, str);
        }
        InternalSignatureVerifier internalSignatureVerifier = new InternalSignatureVerifier(A01.A02());
        allVerifiers.add(internalSignatureVerifier);
        return internalSignatureVerifier;
    }

    public final void A01(InternalSignatureVerifier... internalSignatureVerifierArr) {
        if (Binder.getCallingPid() != Process.myPid()) {
            int length = internalSignatureVerifierArr.length;
            if (length != 0) {
                int i = 0;
                do {
                    InternalSignatureVerifier internalSignatureVerifier = internalSignatureVerifierArr[i];
                    TW<String> A0H = internalSignatureVerifier.mCallerVerifier.A00().A02.iterator();
                    while (A0H.hasNext()) {
                        if (OculusConstants.PACKAGE_NAME_SYSTEM_UI.equals(A0H.next())) {
                            return;
                        }
                    }
                    if (!internalSignatureVerifier.mCallerVerifier.A00().A03) {
                        i++;
                    } else {
                        return;
                    }
                } while (i < length);
                SecurityException securityException = new SecurityException("Cannot access userserver api");
                Mi.A08(OculusUserManager.TAG, securityException, "API call failed signature verification for caller: %s", internalSignatureVerifierArr[0].mCallerVerifier.A00());
                throw securityException;
            }
            throw new IllegalArgumentException("Empty list of allowed apps");
        }
    }
}
