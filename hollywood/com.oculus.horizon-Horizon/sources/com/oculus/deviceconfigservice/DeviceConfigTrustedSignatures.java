package com.oculus.deviceconfigservice;

import android.content.pm.Signature;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.signature.SignatureHelper;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum DeviceConfigTrustedSignatures {
    FBANDROID_FIRST_PARTY_DEBUG_SIG(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG),
    OCULUS_FIRST_PARTY_PROD_SIG(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG);
    
    public final Signature mSignature;

    /* access modifiers changed from: public */
    DeviceConfigTrustedSignatures(Signature signature) {
        this.mSignature = signature;
    }

    public Signature getSignature() {
        return this.mSignature;
    }
}
