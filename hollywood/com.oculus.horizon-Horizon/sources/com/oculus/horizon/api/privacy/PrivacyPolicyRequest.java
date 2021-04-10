package com.oculus.horizon.api.privacy;

import com.oculus.horizon.api.tos.TermsOfServiceRequest;
import com.oculus.horizon.api.tos.TosType;

public class PrivacyPolicyRequest extends TermsOfServiceRequest {
    public PrivacyPolicyRequest(String str) {
        super(str);
        this.type = TosType.PRIVACY_POLICY.toString();
    }
}
