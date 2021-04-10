package com.oculus.horizon.api.tos;

import com.oculus.http.core.base.ApiRequest;

public class TermsOfServiceRequest extends ApiRequest<TermsOfServiceResponse> {
    public final String locale;
    public String type = TosType.TOS.toString();

    public TermsOfServiceRequest(String str) {
        this.locale = str;
    }
}
