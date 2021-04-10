package com.oculus.signature.inject;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_signature_inject_SignatureCheckProvider_ULSEP_BINDING_ID"})
@ApplicationScoped
public class SignatureChecker {
    public static volatile SignatureChecker _UL__ULSEP_com_oculus_signature_inject_SignatureChecker_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final SignatureCheckProvider mSignatureCheckProvider;

    public static class NotFirstPartySignatureException extends RuntimeException {
        public NotFirstPartySignatureException(SignatureCheck signatureCheck) {
            super(StringFormatUtil.formatStrLocaleSafe("First party signature check failed! %s", signatureCheck));
        }
    }

    @Inject
    public SignatureChecker(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mSignatureCheckProvider = (SignatureCheckProvider) AnonymousClass117.A00(140, r3);
    }
}
